package com.dotdash.recruiting.server.models;

import java.util.List;

public class BooksResponse {
	private List<Book> books;
	private int numberOfPages;
	
	public BooksResponse() {
		
	}
	
	public BooksResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	
}
