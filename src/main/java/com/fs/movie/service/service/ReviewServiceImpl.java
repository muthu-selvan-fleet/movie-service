/**
 * 
 */
package com.fs.movie.service.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fs.movie.service.model.Review;
import com.fs.movie.service.repository.ReviewRepository;

/**
 * @author fs_ms
 *
 */
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public ResponseEntity<List<Review>> getAllRevies() {
		final var reviews = reviewRepository.findAll();
		
		return CollectionUtils.isEmpty(reviews) ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
				new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Review>> getAllReviesByUserId(final long userId) {
		final var reviews = reviewRepository.findByUserId(userId);
		
		return CollectionUtils.isEmpty(reviews) ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
				new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Review>> getAllReviesByMovieId(final long movieId) {
		final var reviews = reviewRepository.findByMovieId(movieId);
		
		return CollectionUtils.isEmpty(reviews) ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
				new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Review> addReview(final Review inputReview) {
		final var reviewFromDb = reviewRepository.findByUserIdAndMovieId(
				inputReview.getUserId(), inputReview.getMovieId());
		if(Objects.isNull(reviewFromDb)) {
			reviewRepository.save(inputReview);
			return new ResponseEntity<>(inputReview, HttpStatus.CREATED);
		}
		
		// As per Global Rule, User can add 1 review for the single movie.
		// If the review already exists for the same user id and movie id
		// then, process it as conflict.
		return new ResponseEntity<>(reviewFromDb, HttpStatus.CONFLICT);
	}

	@Override
	public ResponseEntity<Review> updateReview(final long reviewId, final Review inputReview) {
		final var reviewOptional = reviewRepository.findById(reviewId);
		
		if(reviewOptional.isPresent()) {
			final var review = reviewOptional.get();
			review.setReview(inputReview.getReview());
			review.setUpvote(inputReview.getUpvote());
			
			reviewRepository.save(review);
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public void deleteAllReviews() {
		final var allReviews = reviewRepository.findAll();
		allReviews.forEach(eachReview -> reviewRepository.delete(eachReview));
	}

}
