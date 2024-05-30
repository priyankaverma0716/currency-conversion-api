package com.infy.currencyconverter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggingTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggingTest.class);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testLogging() {
        logger.info("User SSN: 123-45-6789, Credit Card: 4111-1111-1111-1111, Email: user@example.com");
        String logOutput = outContent.toString();
        assertTrue(logOutput.contains("User SSN: XXX-XX-XXXX"));
        assertTrue(logOutput.contains("Credit Card: XXXX-XXXX-XXXX-XXXX"));
        assertTrue(logOutput.contains("Email: ***@***"));
    }
}
