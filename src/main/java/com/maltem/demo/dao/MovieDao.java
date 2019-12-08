package com.maltem.demo.dao;

import com.maltem.demo.model.Movie;
import org.json.simple.JSONObject;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:1234")
public interface MovieDao {
    public List<Movie>  findAll() throws IOException;
    public  Movie addMovie(Movie movie) throws IOException;
    public  void removeMovie(String title);
    public  void updateMovie( String title,Movie mv) throws IOException;
    public Movie getMovieByTitle(String title);
}
