package com.infy.currencyconverter.service;

import com.infy.currencyconverter.config.MaskingJsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    private final MaskingJsonFormatter maskingJsonFormatter;

    @Autowired
    public MyService(MaskingJsonFormatter maskingJsonFormatter) {
        this.maskingJsonFormatter = maskingJsonFormatter;
    }

    @Loggable("Logging sensitive information")
    public void logSensitiveInformation() {
        String firstName = "Sourabh";
        String lastName = "Verma";
        String email = "user@example.com";
        String phoneNumber = "222-222-2222";

        // Create a map of sensitive information
        Map<String, String> sensitiveInfo = new HashMap<>();
        sensitiveInfo.put("firstName", firstName);
        sensitiveInfo.put("lastName", lastName);
        sensitiveInfo.put("email", email);
        sensitiveInfo.put("phoneNumber", phoneNumber);

        // Convert to JSON string with masking
        String jsonString = maskingJsonFormatter.toJsonString(sensitiveInfo);

        // Log the masked JSON string
        logger.info(jsonString);

        // Log sensitive information
        logger.info("firstName: {}, lastName: {}, email: {}, phoneNumber: {}", firstName, lastName, email, phoneNumber);

        // Convert to XML string with masking
        String xmlString = toXmlString(sensitiveInfo);

        // Log the masked XML string
        logger.info(xmlString);
    }

    private String toXmlString(Map<String, String> data) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<data>");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            xmlBuilder.append("<").append(entry.getKey()).append(">")
                    .append(entry.getValue())
                    .append("</").append(entry.getKey()).append(">");
        }
        xmlBuilder.append("</data>");
        return xmlBuilder.toString();
    }

    // Test call to log sensitive information
    public static void main(String[] args) {
        MyService myService = new MyService(new MaskingJsonFormatter());
        myService.logSensitiveInformation();
    }
}
