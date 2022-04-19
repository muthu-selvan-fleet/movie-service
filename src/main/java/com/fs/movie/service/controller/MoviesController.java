package com.fs.movie.service.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.Movie;
import com.fs.movie.service.model.MovieModel;
import com.fs.movie.service.service.MovieService;

@RestController
@CrossOrigin(value = "*")
public class MoviesController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/public/recommendedMovies/{userId}")
	public ResponseEntity<List<Movie>> getRecommendedMovies(
		@NotNull @PathVariable("userId") final long userId) {

		return movieService.getRecommendedMovies(userId);
	}

	@GetMapping("/public/sortedMovies")
	public ResponseEntity<Map<String, Object>> getAllMovies(
			@RequestParam(defaultValue = "0") final int page,
			@RequestParam(defaultValue = "10") final int size, 
			@RequestParam(defaultValue = "id,desc") final String[] sort) {

		var response = new HashMap<String, Object>();
		var orders = new ArrayList<Order>();

		try {

			if (sort[0].contains(",")) {

				for (final String sortOrder : sort) {
					final String[] sortArray = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortArray[1]), sortArray[0]));
				}
			} else {
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}

			final Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			final ResponseEntity<Page<Movie>> moviePageRespEntity = movieService.getAllMovies(pagingSort);
			
			if(moviePageRespEntity.getStatusCode() == HttpStatus.OK) {
				final var moviePage = moviePageRespEntity.getBody();
				final var movies = moviePage.getContent();
				
				if (CollectionUtils.isEmpty(movies)) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					response.put("movies", movies);
					response.put("currentPage", moviePage.getNumber());
					response.put("totalItems", moviePage.getTotalElements());
					response.put("totalPages", moviePage.getTotalPages());
				}
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}


	@PostMapping("/movie")
	public ResponseEntity<Movie> addMovie(@RequestBody final MovieModel movieModel) {
		
		final var movie = new Movie();
		final var genreLst = new ArrayList<Genre>();
		
		movie.setId(movieModel.getId());
		movie.setName(movieModel.getName());
		movie.setUpVoteCount(movieModel.getUpVoteCount());
		movie.setDownVoteCount(movieModel.getDownVoteCount());
		
		movieModel.getGenre().forEach(eachGenre -> {
			var genre = new Genre();
			genre.setGenre(eachGenre);
			genreLst.add(genre);
		});
		
		movie.setGenre(genreLst);
		
		
		try {
			movie.setReleaseDate(new SimpleDateFormat("dd-MM-yyyy").parse(movieModel.getReleaseDate()));
			return movieService.addMovie(movie);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Sort.Direction getSortDirection(final String direction) {
		return direction.isBlank() || direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
	}

}
