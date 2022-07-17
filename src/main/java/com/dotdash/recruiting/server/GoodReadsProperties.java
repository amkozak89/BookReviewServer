package com.dotdash.recruiting.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("goodreads")
public record GoodReadsProperties(String goodReadsBaseUrl, String goodReadsKey) {
	
}