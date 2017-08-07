package pl.spring.demo.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookRestServiceTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(bookService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testShouldGetAllBooks() throws Exception {

		// given:
		final BookTo bookTo1 = new BookTo(1L, "title", "Author1", BookStatus.FREE);

		// register response for bookService.findAllBooks() mock
		Mockito.when(bookService.findAllBooks()).thenReturn(Arrays.asList(bookTo1));
		// when
		ResultActions response = this.mockMvc.perform(get("/rest/books").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].id").value(bookTo1.getId().intValue()))
				.andExpect(jsonPath("[0].title").value(bookTo1.getTitle()))
				.andExpect(jsonPath("[0].authors").value(bookTo1.getAuthors()));
	}

	@Test
	public void testShouldSaveBook() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/bookToSave.json");
		String json = FileUtils.readFileToString(file);
		// when
		ResultActions response = this.mockMvc.perform(put("/rest/books").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(status().isOk());
	}

	@Test
	public void shouldDeleteSpecifiedBook() throws Exception {
		// given
		String id = "5";

		// when
		Mockito.doNothing().when(bookService).deleteBook(Long.parseLong(id));
		ResultActions response = mockMvc.perform(delete("/rest/books/?id={id}", id));

		// then
		response.andExpect(status().isOk());
	}

	@Test
	public void shouldGetBooksByIds() throws Exception {
		// given
		String ids = "1,2";
		List<BookTo> result = Arrays.asList(new BookTo(1L, "Title", "Author", BookStatus.FREE),
				new BookTo(2L, "NewTitle", "NewAuthor", BookStatus.FREE));

		// when
		Mockito.when(bookService.findBookById(1L)).thenReturn(result.get(0));
		Mockito.when(bookService.findBookById(2L)).thenReturn(result.get(1));
		ResultActions response = mockMvc.perform(get("/rest/books/{ids}", ids));

		// then
		response.andExpect(status().isOk()).andExpect(jsonPath("[0].id").value(result.get(0).getId().intValue()))
				.andExpect(jsonPath("[1].id").value(result.get(1).getId().intValue()));

	}
}
