package pl.spring.demo.web.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.controller.BookController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	BookService bookService;

	@InjectMocks
	private BookController bookController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		Mockito.reset(bookService);
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		;
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(bookController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void shouldGetAllBooks() throws Exception {
		// given
		List<BookTo> books = getBooks();

		// when
		when(bookService.findAllBooks()).thenReturn(books);
		// then
		mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(view().name(ViewNames.BOOKS))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, books));
	}

	@Test
	public void shouldGetBookByAuthorAndTitle() throws Exception {

		// given
		String author = "Ex";
		String title = "Ex";
		BookTo book = new BookTo(1L, "Example Title", "Example Author", BookStatus.FREE);
		List<BookTo> books = new ArrayList<>();
		books.add(book);

		// when
		when(bookService.findBooksByAuthorAndTitle(author, title)).thenReturn(books);
		ResultActions resultActions = mockMvc.perform(get("/books/?author=Ex&title=Ex"));

		// then

		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.BOOKS))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, books));

	}

	@Test
	public void shouldDeleteBook() throws Exception {
		// given
		String title = "Example";

		// when
		doNothing().when(bookService).deleteBook(5L);
		ResultActions resultActions = mockMvc.perform(get("/books/delete?id=5&title=Example"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.DELETED))
				.andExpect(model().attribute("title", title));
	}

	@Test
	public void shouldOpenBookAddView() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/add"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.ADD_BOOK));

	}

	@Test
	public void shouldAddNewBook() throws Exception {
		// given
		BookTo book = new BookTo(5L, "Janusz", "Ksionszka", BookStatus.FREE);

		// when
		when(bookService.saveBook(book)).thenReturn(book);
		ResultActions resultActions = mockMvc.perform(post("/books/add").param("title", book.getTitle())
				.param("authors", book.getAuthors()).param("status", book.getStatus().toString()));

		// then
		resultActions.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/books"));
	}

	private List<BookTo> getBooks() {
		List<BookTo> result = new ArrayList<>();
		result.add(new BookTo(1L, "Book", "Janusz", BookStatus.FREE));
		result.add(new BookTo(2L, "Book2", "Janusz", BookStatus.FREE));

		return result;
	}

}