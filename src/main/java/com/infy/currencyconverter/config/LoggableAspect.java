package com.infy.currencyconverter.config;

import com.infy.currencyconverter.service.Loggable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggableAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggableAspect.class);

    @Autowired
    private MaskingJsonFormatter jsonFormatter;

    @Pointcut("@annotation(loggable)")
    public void executeLoggable(Loggable loggable) {
    }

    @AfterReturning(pointcut = "executeLoggable(loggable)", returning = "returnValue")
    public void logMethodExecution(JoinPoint joinPoint, Loggable loggable, Object returnValue) {
        String message = loggable.value();
        if (message.isEmpty()) {
            message = getDefaultMessage(joinPoint);
        }
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("message", message);
        logger.info(jsonFormatter.toJsonString(logMap));
    }

    private String getDefaultMessage(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName() + " executed successfully";
    }
}
