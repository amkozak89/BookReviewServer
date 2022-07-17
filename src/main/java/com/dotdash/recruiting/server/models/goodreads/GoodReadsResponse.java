package com.dotdash.recruiting.server.models.goodreads;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GoodreadsResponse")
public class GoodReadsResponse {
	private Search search;
	
	public GoodReadsResponse() {
		
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}	
}