package com.infy.currencyconverter.config;

import ch.qos.logback.contrib.json.JsonFormatter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

@Component
public class MaskingJsonFormatter implements JsonFormatter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Properties maskProperties;

    public MaskingJsonFormatter() {
        maskProperties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("mask.properties")) {
            if (input != null) {
                maskProperties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toJsonString(Map map) {
        for (Object keyObj : map.keySet()) {
            if (keyObj instanceof String) {
                String key = (String) keyObj;
                Object valueObj = map.get(key);
                if (valueObj instanceof String) {
                    String value = (String) valueObj;
                    if (isJson(value)) {
                        map.put(key, maskJson(value));
                    } else if (isXml(value)) {
                        map.put(key, maskXml(value));
                    } else {
                        map.put(key, maskString(value));
                    }
                }
            }
        }
        try {
            ObjectNode objectNode = objectMapper.valueToTree(map);
            return objectMapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isJson(String message) {
        try {
            objectMapper.readTree(message);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isXml(String message) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.parse(new ByteArrayInputStream(message.getBytes()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String maskJson(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            if (jsonNode.isObject()) {
                ObjectNode objectNode = (ObjectNode) jsonNode;
                for (String field : maskProperties.stringPropertyNames()) {
                    if (objectNode.has(field)) {
                        String maskedValue = maskProperties.getProperty(field);
                        objectNode.put(field, maskedValue);
                    }
                }
                return objectMapper.writeValueAsString(objectNode);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String maskXml(String message) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(message.getBytes()));

            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nodeName = element.getNodeName();
                    if (maskProperties.containsKey(nodeName)) {
                        element.setTextContent(maskProperties.getProperty(nodeName));
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));

            return writer.toString();
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String maskString(String message) {
        for (String field : maskProperties.stringPropertyNames()) {
            if (message.contains(field)) {
                String extractedValue = extractAndMaskValue(message, field);
                String maskedValue = maskProperties.getProperty(field);
                message = message.replace(field + ": " + extractedValue, field + ": " + maskedValue);
            }
        }
        return message;
    }

    private String extractAndMaskValue(String value, String field) {
        String[] parts = value.split(",|:");
        for (int i = 0; i < parts.length; i += 2) {
            String key = parts[i].trim();
            if (key.equalsIgnoreCase(field)) {
                return parts[i + 1].trim();
            }
        }
        return value;
    }
}
