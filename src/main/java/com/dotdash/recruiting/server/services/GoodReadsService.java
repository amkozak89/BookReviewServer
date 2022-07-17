package com.dotdash.recruiting.server.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.dotdash.recruiting.server.models.goodreads.GoodReadsResponse;

public class GoodReadsService {

	private HttpClient client;
	private String goodReadsBaseUrl;
	private String goodReadsSearchPath = "/search/index.xml";
	private String goodReadsKey;

	/**
	 * Constructor
	 * 
	 * @param client - HttpClient
	 */
	public GoodReadsService(HttpClient client, String baseUrl, String key) {
		this.client = client;
		this.goodReadsBaseUrl = baseUrl;
		this.goodReadsKey = key;
	}

	/**
	 * Perform Search against GoodReads
	 * Any exceptions thrown up to caller to preserver error information when handling/logging in the controller
	 * @param query - search query
	 * @param page  - page number
	 * @return GoodReadsResponse containing data
	 * @throws JAXBException        - On failure to unmarshal GoodReads XML
	 * @throws URISyntaxException   - On failure to create valid URI
	 * @throws IOException          - On failure to get a response from GoodReads
	 *                              service
	 * @throws InterruptedException - On failure to get a response from GoodReads
	 *                              service
	 * @throws ExecutionException   - On failure in processing async Future
	 */
	public GoodReadsResponse search(String query, int page)
			throws JAXBException, URISyntaxException, IOException, InterruptedException, ExecutionException {
		// build request
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https", goodReadsBaseUrl, goodReadsSearchPath,
						"key=" + goodReadsKey + "&q=" + query + "&page=" + page, null))
				.GET().build();

		// get response (I'm a little vague on Java specific practices here, in .NET
		// using async is generally the rule)
		CompletableFuture<HttpResponse<InputStream>> response = client.sendAsync(request, BodyHandlers.ofInputStream());

		// parse response and return
		JAXBContext context = JAXBContext.newInstance(GoodReadsResponse.class);
		return (GoodReadsResponse) context.createUnmarshaller().unmarshal(response.get().body());
	}
}
