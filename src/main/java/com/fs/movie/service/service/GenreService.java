/**
 * 
 */
package com.fs.movie.service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.User;

/**
 * @author fs_ms
 *
 */
public interface GenreService {

	ResponseEntity<User> addGenreToUser(final long userId, final String genre);

	ResponseEntity<List<Genre>> getAllGenre();

	ResponseEntity<Genre> updateGenre(final long genreId, final String genre);

	void deleteAllGenre();
}
