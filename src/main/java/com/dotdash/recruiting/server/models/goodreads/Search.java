package com.dotdash.recruiting.server.models.goodreads;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "search")
public class Search {
	private List<Work> results;
	private int resultsStart;
	private int resultsEnd;
	private int totalResults;
	private String source;
	private String queryTimeSeconds;
	
	public Search() {
		
	}

	public List<Work> getResults() {
		return results;
	}
	
	@XmlElement(name = "work")
	@XmlElementWrapper(name = "results")
	public void setResults(List<Work> results) {
		this.results = results;
	}

	public int getResultsStart() {
		return resultsStart;
	}

	@XmlElement(name = "results-start")
	public void setResultsStart(int resultsStart) {
		this.resultsStart = resultsStart;
	}

	public int getResultsEnd() {
		return resultsEnd;
	}

	@XmlElement(name = "results-end")
	public void setResultsEnd(int resultsEnd) {
		this.resultsEnd = resultsEnd;
	}

	public int getTotalResults() {
		return totalResults;
	}

	@XmlElement(name = "total-results")
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getQueryTimeSeconds() {
		return queryTimeSeconds;
	}
	
	@XmlElement(name = "query-time-seconds")
	public void setQueryTimeSeconds(String queryTimeSeconds) {
		this.queryTimeSeconds = queryTimeSeconds;
	}
	
	
}
