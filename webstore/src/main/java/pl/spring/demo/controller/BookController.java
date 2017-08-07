package pl.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ModelAndView deleteBook(@RequestParam("id") String id,
			@RequestParam(value = "title", required = false) String title) {

		long bookId = Long.parseLong(id);
		bookService.deleteBook(bookId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", title);
		modelAndView.setViewName(ViewNames.DELETED);

		return modelAndView;
	}

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

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView newBookModel() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.ADD_BOOK);

		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String book(@ModelAttribute("SpringWeb") BookTo book, Model model) {
		model.addAttribute("title", book.getTitle());
		model.addAttribute("authors", book.getAuthors());
		model.addAttribute("status", book.getStatus());
		bookService.saveBook(book);

		return "redirect:/books";
	}

	@ModelAttribute("newBook")
	public BookTo getBookTo() {
		return new BookTo();
	}

	/**
	 * Binder initialization
	 */

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
