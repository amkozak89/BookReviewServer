package com.dotdash.recruiting.server.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dotdash.recruiting.server.models.Book;
import com.dotdash.recruiting.server.models.BooksResponse;
import com.dotdash.recruiting.server.models.goodreads.GoodReadsResponse;
import com.dotdash.recruiting.server.services.GoodReadsService;

@RestController
public class BooksController {

	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);
	
	private GoodReadsService service;
	

	/**
	 * Constructor
	 * @param service - GoodReads data service, retrieved via Autowiring
	 */
	public BooksController(GoodReadsService service) {
		this.service = service;
	}

	/**
	 * GET to search books
	 * @param query - Query parameter to search
	 * @param sortBy - Sort By Option: "title" or "author" (case ignored)
	 * @param page - Page number to get
	 * @return BooksResponse containing total pages and list of books
	 */
	@GetMapping("/books")
	public BooksResponse search(@RequestParam(value = "query") String query,
			@RequestParam(value = "sortBy") Optional<String> sortBy,
			@RequestParam(value = "page") Optional<Integer> page) {

		try {
			// any value other than "author" (ignore case) will be treated as title.
			// In reality, this may not be desired. We may want to fail if anything other
			// than "title" or "author" is received to provide early feedback that something's wrong
			boolean sortByTitle = !sortBy.orElse("title").equalsIgnoreCase("author");

			// get response from the server
			GoodReadsResponse response = service.search(query, page.orElse(1));

			// calculate the total number of pages available from the results to return to client
			int numberOfPages = (int) Math.ceil((double) response.getSearch().getTotalResults()
					/ (response.getSearch().getResultsEnd() - response.getSearch().getResultsStart() + 1));

			// transform response's works to our Book response model, sort by sort option
			List<Book> books = response.getSearch().getResults().stream().map(work -> {
				Book book = new Book();
				book.setAuthor(work.getBook().getAuthor().getName());
				book.setTitle(work.getBook().getTitle());
				book.setImageUrl(work.getBook().getImageUrl());
				return book;
			}).sorted((book1, book2) -> sortByTitle ? book1.getTitle().compareTo(book2.getTitle())
					: book1.getAuthor().compareTo(book2.getAuthor())).collect(Collectors.toList());

			// build response object and return
			BooksResponse result = new BooksResponse(books);
			result.setNumberOfPages(numberOfPages);

			return result;

		} catch (JAXBException | URISyntaxException | IOException | InterruptedException | ExecutionException e) {
			// return an error status
			// in a larger application this would be handled elsewhere, either at a global or controller level.
			logger.error("Error with query: \"" + query + "\"\nsortBy: " + sortBy + "\npage: " + page, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There was an error processing the request.", e);
		} 
	}
}
