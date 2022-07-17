package com.dotdash.recruiting.server.models.goodreads;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "best_book")
public class BestBook {
	private int id;
	private String title;
	private Author author;
	private String imageUrl;
	
	public BestBook() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@XmlElement(name = "image_url")
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
