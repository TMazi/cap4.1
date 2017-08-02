package pl.spring.demo.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Book controller
 * 
 * @author mmotowid
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	/**
	 * Method collects info about all books
	 */
	@RequestMapping
	public ModelAndView allBooks() {
		ModelAndView modelAndView = new ModelAndView();
		List<BookTo> books = bookService.findAllBooks();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, books);

		return modelAndView;
	}

	@RequestMapping("/")
	public ModelAndView findBooksByCriteria(@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "title", required = false) String title) {

		List<BookTo> books = bookService.findBooksByAuthorAndTitle(title, author);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, books);

		return modelAndView;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("id") String id) {
		
		long bookId = Long.parseLong(id);
		bookService.deleteBook(bookId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.DELETED);
		
		return modelAndView;
	}

	// TODO: here implement methods which displays book info based on query
	// arguments
	@RequestMapping("/book")
	public ModelAndView bookDetails(@RequestParam("id") String bookId) {
		long id = Long.parseLong(bookId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOK);
		BookTo book = bookService.findBookById(id);
		modelAndView.addObject(ModelConstants.BOOK, book);
		modelAndView.addObject("bookId", bookId);

		return modelAndView;

	}

	// TODO: Implement GET / POST methods for "add book" functionality

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
