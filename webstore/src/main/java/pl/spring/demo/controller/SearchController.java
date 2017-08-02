package pl.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	BookService bookService;

	@RequestMapping
	public ModelAndView searchFormula() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.SEARCH);

		return modelAndView;
	}

	@RequestMapping("/")
	public ModelAndView findBooksByCriteria(@RequestParam("author") String author,
			@RequestParam("title") String title) {
		
		List<BookTo> books = bookService.findBooksByAuthorAndTitle(title, author);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST, books);

		return modelAndView;
	}

}
