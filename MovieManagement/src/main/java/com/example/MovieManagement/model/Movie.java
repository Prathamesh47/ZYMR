package com.example.MovieManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Document(collection = "movies")
public class Movie {
    public Movie(String id, String title, String director, int releaseYear, String genre, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "Title is required")
    private String title;

    private String director;

    @Min(value = 1900, message = "Release year should be valid")
    @Max(value = 2100, message = "Release year should be valid")
    private int releaseYear;

    private String genre;

    @Min(value = 1)
    @Max(value = 10)
    private double rating;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
