/**
 * 
 */
package com.fs.movie.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fs.movie.service.model.Genre;

/**
 * @author fs_ms
 *
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query(value = "SELECT DISTINCT GENRE_VALUE FROM GENRE", nativeQuery = true)
	List<Genre> getAllGenre();
}
