/**
 * 
 */
package com.fs.movie.service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fs.movie.service.model.Review;

/**
 * @author fs_ms
 *
 */
public interface ReviewService {

	ResponseEntity<List<Review>> getAllRevies();

	ResponseEntity<List<Review>> getAllReviesByUserId(final long userId);

	ResponseEntity<List<Review>> getAllReviesByMovieId(final long movieId);

	ResponseEntity<Review> addReview(final Review review);
	
	void deleteAllReviews();

	ResponseEntity<Review> updateReview(final long reviewId, final Review review);

}
