package com.example.MovieManagement.controller;

import com.example.MovieManagement.model.Movie;
import com.example.MovieManagement.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies with pagination")
    @GetMapping
    public Page<Movie> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return movieService.getMoviesPaginated(pageable);
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
