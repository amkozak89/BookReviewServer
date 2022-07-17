package com.dotdash.recruiting.server.models.goodreads;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "work")
public class Work {
	private int id;
	private int booksCount;
	private int ratingsCount;
	private int textReviewsCount;
	private int originalPublicationYear;
	private int originalPublicationMonth;
	private int originalPublicationDay;
	private double averageRating;
	private BestBook book;
	
	public Work() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBooksCount() {
		return booksCount;
	}

	public void setBooksCount(int booksCount) {
		this.booksCount = booksCount;
	}

	public int getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public int getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(int textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public int getOriginalPublicationYear() {
		return originalPublicationYear;
	}

	public void setOriginalPublicationYear(int originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}

	public int getOriginalPublicationMonth() {
		return originalPublicationMonth;
	}

	public void setOriginalPublicationMonth(int originalPublicationMonth) {
		this.originalPublicationMonth = originalPublicationMonth;
	}

	public int getOriginalPublicationDay() {
		return originalPublicationDay;
	}

	public void setOriginalPublicationDay(int originalPublicationDay) {
		this.originalPublicationDay = originalPublicationDay;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public BestBook getBook() {
		return book;
	}

	@XmlElement(name = "best_book")
	public void setBook(BestBook book) {
		this.book = book;
	}
	
	
}
