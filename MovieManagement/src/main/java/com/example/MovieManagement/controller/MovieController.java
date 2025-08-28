package com.example.MovieManagement.controller;

import com.example.MovieManagement.model.Movie;
import com.example.MovieManagement.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies")
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @Operation(summary = "Get movie by ID")
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @Operation(summary = "Create a new movie")
    @PostMapping
    public ResponseEntity<Movie> createMovie(@Validated @RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @Operation(summary = "Update existing movie")
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable String id, @Validated @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @Operation(summary = "Delete movie by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
