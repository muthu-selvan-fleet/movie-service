package com.fs.movie.service.service;

import java.util.List;

import com.fs.movie.service.model.MovieModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.fs.movie.service.model.Movie;

public interface MovieService {

	ResponseEntity<Page<Movie>> getAllMovies(final Pageable pagingSort);

	ResponseEntity<Movie> addMovie(final Movie movie);

	ResponseEntity<List<Movie>> getRecommendedMovies(final long userId);
	
	void deleteAllMovies();

	ResponseEntity<Page<Movie>> findBy(final MovieModel movieModel, final Pageable pagingSort);
}
