package com.maltem.demo;

import com.maltem.demo.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllMovies() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/movies",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
		Movie[] movies = restTemplate.getForObject(getRootUrl() + "/movies/", Movie[].class);
		assert(movies.length> 0);
	}

	@Test
	public void testGetMovieById() {
		Movie movie = restTemplate.getForObject(getRootUrl() + "/movies/John Wick", Movie.class);
		System.out.println(movie.toString());
		assertNotNull(movie);
	}

	@Test
	public void testCreateMovie() {
		Movie[] movies = restTemplate.getForObject(getRootUrl() + "/movies/", Movie[].class);
		int l=movies.length;
		Movie movie = new Movie("Rabab", "Rabab", "08/12/2019", "demo");
		ResponseEntity<Movie> postResponse = restTemplate.postForEntity(getRootUrl() + "/movies", movie, Movie.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		movies = restTemplate.getForObject(getRootUrl() + "/movies/", Movie[].class);
		assert(movies.length> l);
	}

	@Test
	public void testUpdateMovie() {
		String title = "Rabab";
		Movie movie = restTemplate.getForObject(getRootUrl() + "/movies/" + title, Movie.class);
		movie.setTitle("admin");
		restTemplate.put(getRootUrl() + "/Movies/" + title, movie);

		Movie updatedMovie = restTemplate.getForObject(getRootUrl() + "/movies/" + title, Movie.class);
		assertNotNull(updatedMovie);
	}

	@Test
	public void testDeleteMovie() {
		Movie[] movies = restTemplate.getForObject(getRootUrl() + "/movies/", Movie[].class);
		int l=movies.length;String title = "Rabab";
		Movie movie = restTemplate.getForObject(getRootUrl() + "/movies/" + title, Movie.class);
		assertNotNull(movie);

		restTemplate.delete(getRootUrl() + "/movies/" + title);

		try {
			movie = restTemplate.getForObject(getRootUrl() + "/movies/" + title, Movie.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
		movies = restTemplate.getForObject(getRootUrl() + "/movies/", Movie[].class);
		assert(movies.length< l);

	}
}
