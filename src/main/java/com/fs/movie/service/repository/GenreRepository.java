/**
 * 
 */
package com.fs.movie.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fs.movie.service.model.Genre;

/**
 * @author fs_ms
 *
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query(value = "SELECT * FROM GENRE WHERE movie_id is NULL and user_id is NULL", nativeQuery = true)
	List<Genre> getAllGenre();
	
	@Query(value = "SELECT * FROM GENRE WHERE user_id = :userId ", nativeQuery = true)
	List<Genre> getAllGenreByUserId( @Param("userId") long userId);
}
