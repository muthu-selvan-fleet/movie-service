package com.fs.movie.service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.fs.movie.service.model.Movie;

public interface MovieService {

	ResponseEntity<Page<Movie>> getAllMovies(final Pageable pagingSort);

	ResponseEntity<Movie> addMovie(final Movie movie);

	ResponseEntity<List<Movie>> getRecommendedMovies(final long userId);
	
	void deleteAllMovies();
	
}
