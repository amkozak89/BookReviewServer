package com.dotdash.recruiting.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GoodReadsProperties.class)
public class BookReviewServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewServerApplication.class, args);
	}
}
