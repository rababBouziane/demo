package com.maltem.demo.web.controller;

import com.maltem.demo.dao.MovieDao;
import com.maltem.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
@CrossOrigin(origins = "http://localhost:1234")
@RestController
public class movieController {
    @Autowired
    private MovieDao movieDao;

    @RequestMapping(value="/movies", method= RequestMethod.GET)
    public List<Movie>  moviesList() throws IOException {
         return movieDao.findAll();
    }

    @RequestMapping(value = "/movies/{title}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable String title) {
        return movieDao.getMovieByTitle(title);
    }

    @PostMapping(value = "/movies")
    public Movie addMovie(@RequestBody Movie movie) throws IOException {
        return movieDao.addMovie(movie);
    }

    @DeleteMapping (value = "/movies/{title}")
    public void deleteMovie(@PathVariable String title) {
        movieDao.removeMovie(title);
    }

    @PutMapping (value = "/movies/{title}")
    public void updateMovie(@PathVariable String title,@RequestBody Movie movie) throws IOException {
        movieDao.updateMovie(title, movie);
    }
}