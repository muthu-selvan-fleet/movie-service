/**
 * 
 */
package com.fs.movie.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fs.movie.service.model.Review;

/**
 * @author fs_ms
 *
 */
@Repository
public interface ReviewRepository extends  JpaRepository<Review, Long> {

	@Query("update Review r set r.review = :review where id = :id")
	void updateReview(@Param(value = "id") final long id, 
			@Param(value = "review") final String review);

	List<Review> findByUserId(final long userId);

	List<Review> findByMovieId(final long movieId);
	
	Review findByUserIdAndMovieId(final long userId, final long movieId);
}
