package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.controller.SearchController;
import pl.spring.demo.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

	@InjectMocks
	private SearchController searchController;

	@Mock
	BookService bookService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		;
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(searchController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void shouldOpenSearchView() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/search"));

		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.SEARCH));
	}

}
