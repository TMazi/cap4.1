package pl.spring.demo.service;

import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookService {

	BookTo findBookById(long id);
    List<BookTo> findAllBooks();
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);
    List<BookTo> findBooksByAuthorAndTitle(String title, String author);

    BookTo saveBook(BookTo book);
    void deleteBook(Long id);
}
