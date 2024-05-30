package com.infy.currencyconverter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CurrencyConverterApplication {

	private static final Logger log = LoggerFactory.getLogger(CurrencyConverterApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
		logSensitiveData();

	}
	private static void logSensitiveData() {
		String message = "firstName: Sourabh, lastName: Verma, email: abc@gmail.com, phoneNumber: 223-222-2222";
		log.info(message);
	}
}
