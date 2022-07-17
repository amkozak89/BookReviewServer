package com.dotdash.recruiting.server;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.dotdash.recruiting.server.services.GoodReadsService;

@Configuration
public class AppConfig {

	@Autowired
	public GoodReadsProperties goodReadsProperties;
	
	// Setting scopes to application level.
	// I'm a little rusty for Java and HttpClient best practices 
	// (I'm running on the assumption that HttpClients are expensive to instantiate as they are in C# and should be singletons)
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
	HttpClient httpClient() {
		return HttpClient.newHttpClient();
	}
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
	GoodReadsService goodReadsService(HttpClient client) {
		return new GoodReadsService(client, goodReadsProperties.goodReadsBaseUrl(), goodReadsProperties.goodReadsKey());
	}
}
