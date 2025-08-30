package com.example.MovieManagement;

import com.example.MovieManagement.controller.MovieController;
import com.example.MovieManagement.model.Movie;
import com.example.MovieManagement.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
class MovieManagementApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService movieService;

	@Test
	void testGetAllMoviesWithPagination() throws Exception {
		Movie movie = new Movie("1", "Friends", "Christopher Nolan", 2010, "Sci-Fi", 9.0);
		Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie), PageRequest.of(0, 10), 1);

		given(movieService.getMoviesPaginated(any(Pageable.class))).willReturn(moviePage);

		mockMvc.perform(get("/movies?page=0&size=10"))
				.andExpect(status().isOk());
	}
}
