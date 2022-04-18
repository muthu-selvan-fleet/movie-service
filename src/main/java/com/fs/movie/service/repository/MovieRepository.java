package com.fs.movie.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fs.movie.service.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	
	 @Query(value = " select * from movies where movie_id in (select distinct movie_id from  genre where genre_value in (:favGenre))", 
			 nativeQuery = true)
	List<Movie> getRecommendedMoviesWithGenre(@Param("favGenre") final List<String> favGenre);

}
