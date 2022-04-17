/**
 * 
 */
package com.fs.movie.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.User;
import com.fs.movie.service.repository.GenreRepository;
import com.fs.movie.service.repository.UserRepository;

/**
 * @author fs_ms
 *
 */
@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Override
	public ResponseEntity<User> addGenreToUser(final long userId, final String newGenre) {

		final var userOptional = userRepository.findById(userId);

		if (userOptional.isPresent()) {

			try {
				final var user = userOptional.get();

				for (Genre userGenre : user.getFavouriteGenre()) {

					if (userGenre.getGenre().equals(newGenre)) {
						// Genre already exists. So we dont need to once again.
						return new ResponseEntity<>(user, HttpStatus.OK);
					}
				}

				final var genre = new Genre();
				genre.setGenre(newGenre);
				genreRepository.save(genre);

				return new ResponseEntity<>(user, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<List<Genre>> getAllGenre() {
		
		final var genre = genreRepository.getAllGenre();
		return CollectionUtils.isEmpty(genre) ? 
				new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
				new ResponseEntity<>(genre,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Genre> updateGenre(final long genreId, final String inputGenre) {
		final var genreOptional = genreRepository.findById(genreId);
		
		if(genreOptional.isPresent()) {
			
			final Genre genre = genreOptional.get();
			genre.setGenre(inputGenre);
			
			genreRepository.save(genre);
			return new ResponseEntity<>(genre, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public void deleteAllGenre() {
		var allGenre = genreRepository.findAll();
		allGenre.forEach(eachGenre -> genreRepository.delete(eachGenre));
		
	}

}
