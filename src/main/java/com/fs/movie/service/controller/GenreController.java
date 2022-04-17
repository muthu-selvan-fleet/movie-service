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
import org.springframework.web.bind.annotation.RestController;

import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.User;
import com.fs.movie.service.service.GenreService;

/**
 * @author fs_ms
 *
 */

@RestController
@CrossOrigin(value = "*")
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping("/genres") 
	public ResponseEntity<List<Genre>> getAllGenre() {
		return genreService.getAllGenre();
	}
	
	/**
	 * Method to add fav Genre to the User.
	 */
	@PostMapping("/genre/{userId}")
	public ResponseEntity<User> addGenreToUser(@PathVariable("userId") final long userId, 
			final String genre) {
		return genreService.addGenreToUser(userId,genre);
	}
	
	@PutMapping("/genre/{genreId}")
	public ResponseEntity<Genre> updateGenre(@PathVariable("genreId") final long genreId, 
			final String genre) {
		return genreService.updateGenre(genreId, genre);
	}

}
