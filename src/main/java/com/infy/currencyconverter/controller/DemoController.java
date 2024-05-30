package com.infy.currencyconverter.controller;

import com.infy.currencyconverter.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    MyService myService;
    @GetMapping("/test")
    public String testLogging() {
        myService.logSensitiveInformation();
        return "Check the logs!";
    }
}