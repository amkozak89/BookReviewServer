# Book Listing Exercise
This is the server side project for the Book Listing Exercise. The client side is available [here](https://github.com/amkozak89/BookReviewClient)

## Considerations
#### application.properties
 - Ideally, `goodreads.good-reads-key` would be stored as a secret outside of the repo. Since this is a sample project and the key has already been made public, I opted to keep it here for ease of setup and testing.
#### AppConfig
 - I initialized the `HttpClient` here and set it up with an application level scope. I'm a little rusty here, I don't know for certain if the `HttpClient` is as expensive and handled as a Singleton as it is in C#. It seemed appropriate.
 
#### BooksController
 - In a larger application I might argue for an additional layer between `BooksController` and `GoodReadsService` to handle business logic and service model to 'view' model translation.
 I'd like to do as much as possible to explicitly prevent extraneous backend data from bubbling up into the response to clients as it may involve leaking sensitive data. 
 The additional logic also complicates the controller code and reduces compliance with the single responsibility principle. At the same time, it increases complexity of this project with little value yet. I invoked [YAGNI](https://martinfowler.com/bliki/Yagni.html)
 - The API hides possible client-side errors by accepting any `sortBy` value, treating invalid replies as if `title` was passed. This could be a failure state.
 - All exceptions from within the `search` method and the `GoodReadsService.search()` are handled here in a single catch with simple logic. This might be better done at the controller or global level. For now this is only used in one place, it can be elevated to the lowest common ancestor scope if/when more is added.
 That would provide a better way to manage error handling in a single location down the road.

#### GoodReadsService
 - In general this could be cleaner with URI generation and maybe unmarshalling. In a larger application, more of the URI building could be done in a specific UrlBuilder type class and with more templating.
 I opted to avoid that just for the sake of getting things out quickly, it's a good candidate for future iterative improvement.
 - I didn't use interface/abstract classes here. Without context around the bigger picture of future development and where volatility may be, the interface/abstract class would likely be just as volatile. 
 For example, making an abstract class that takes an `HttpClient` could be useful only if many services are expected to rely on Http APIs. If data sources are potentially based on DB or other types, the inheritence hasn't done much yet.
 This could be a candidate for using a `DataSource` type class that abstracts the communication method away from the service, not useful yet here.
 - Any possible exceptions thrown here are thrown up the stack to the Controller to allow it to handle any logging with all information.
 - I opted to use `client.sendAsync()` here. Using Async here may not be as valuable here as it is in .Net since (I think) the C# `async` prevents the thread from being blocked, allowing it to do something else, whereas Java still has to block on the `.get()` until response is received. I'm not 100% sure if this is the best approach over synchronous sending. 
 
 #### models/models.goodreads
  - I made the distinction here just to keep models returned to clients separate from models from data sources to minimize accidental data leakage. Any properties added to what the client receives must be explicitly added.
  
 #### Overall
 There is one issue with regard to the sorting here in conjunction with pagination, we are relying on the reply from GoodReads that doesn't handle sorting.
 In effect, only the current page is being sorted. That means results from one page may not be in proper global order from all results returned by GoodReads across all pages.
 If that is a necessary requirement, it may be more optimal to collect all results from GoodReads and sort prior to return. 
 To do this might require a multi-threaded approach of getting all pages and caching a full search locally, and could violate the [API's terms](https://www.goodreads.com/api/terms) of allowing no more than one method request per second.
