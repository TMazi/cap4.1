package pl.spring.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RestController
@ResponseBody
@RequestMapping("/rest")
public class BookRestService {

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.PUT)
	public BookTo addNewBook(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

	@RequestMapping(value = "/books", method = RequestMethod.DELETE)
	public void deleteBook(@RequestParam("id") long bookId) {
		bookService.deleteBook(bookId);
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<BookTo> getAllBooks() {
		return bookService.findAllBooks();
	}
	
	@RequestMapping(value = "/books/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public List<BookTo> getSpecifiedBook(@PathVariable long[] ids) {
		List<BookTo> result = new ArrayList<>();
		for (long id : ids) {
			result.add(bookService.findBookById(id));
		}
		return result;
	}

}
