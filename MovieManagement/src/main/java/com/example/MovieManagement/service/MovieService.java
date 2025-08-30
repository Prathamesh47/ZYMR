package com.example.MovieManagement.service;

import com.example.MovieManagement.model.Movie;
import com.example.MovieManagement.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getMoviesPaginated(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found with ID: " + id));
    }

    public Movie createMovie(Movie movie) {
        movieRepository.findByTitleAndReleaseYear(movie.getTitle(), movie.getReleaseYear())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("Movie already exists: " + movie.getTitle());
                });
        return movieRepository.save(movie);
    }

    public Movie updateMovie(String id, Movie movie) {
        Movie existing = getMovieById(id);
        movieRepository.findByTitleAndReleaseYear(movie.getTitle(), movie.getReleaseYear())
                .ifPresent(m -> {
                    if (!m.getId().equals(id)) {
                        throw new IllegalArgumentException("Another movie with same title & year exists!");
                    }
                });

        existing.setTitle(movie.getTitle());
        existing.setReleaseYear(movie.getReleaseYear());
        existing.setGenre(movie.getGenre());

        return movieRepository.save(existing);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}
