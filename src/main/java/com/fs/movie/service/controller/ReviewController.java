/**
 * 
 */
package com.fs.movie.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fs.movie.service.model.Review;
import com.fs.movie.service.service.ReviewService;

/**
 * @author fs_ms
 *
 */
@RestController
@CrossOrigin(value = "*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllRevies() {
		return reviewService.getAllRevies();
	}
	
	@GetMapping("/reviews/user/{userId}")
	public ResponseEntity<List<Review>> getAllReviesByUserId(
			@PathVariable("userId") final long userId) {
		return reviewService.getAllReviesByUserId(userId);
	}
	
	@GetMapping("/reviews/movie/{movieId}")
	public ResponseEntity<List<Review>> getAllReviesByMovieId(
			@PathVariable("movieId") final long movieId) {
		return reviewService.getAllReviesByMovieId(movieId);
	}
	
	@PostMapping("/review")
	public ResponseEntity<Review> addReview(@RequestBody final Review review) {
		return reviewService.addReview(review);
	}
	
	@PutMapping("/review/{reviewId}")
	public ResponseEntity<Review> updateReview(
			@PathVariable("reviewId") final long reviewId,
			@RequestBody final Review review) {
		return reviewService.updateReview(reviewId,review);
	}
	
}
