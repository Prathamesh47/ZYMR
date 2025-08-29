package com.example.MovieManagement.repository;

import com.example.MovieManagement.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByTitleAndYear(String title, int year);
}
