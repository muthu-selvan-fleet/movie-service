/**
 * 
 */
package com.fs.movie.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fs.movie.service.model.Movie;
import com.fs.movie.service.repository.GenreRepository;
import com.fs.movie.service.repository.MovieRepository;
import com.fs.movie.service.repository.UserRepository;

/**
 * @author fs_ms
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public ResponseEntity<Page<Movie>> getAllMovies(final Pageable pagingSort) {
		
		final var pagedMovies = movieRepository.findAll(pagingSort);
		return Objects.isNull(pagedMovies) ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(pagedMovies,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Movie> addMovie(final Movie movie) {
		try {
			final var newMovie = new Movie(movie.getName(), movie.getReleaseDate(), movie.getUpVoteCount(), movie.getDownVoteCount());
			movieRepository.save(newMovie);
			movie.getGenre().forEach(eachGenre -> {
				eachGenre.setMovie(newMovie);
				genreRepository.save(eachGenre);
				});
			return new ResponseEntity<>(newMovie,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<Movie>> getRecommendedMovies(final long userId) {

		final var userOptional = userRepository.findById(userId);
		
		if(userOptional.isPresent()) {
			
			final var user = userOptional.get();
			final var genre = new ArrayList<String>();
			 
			final var favouriteGenre = user.getFavouriteGenre();
			favouriteGenre.forEach(eachFavGenre -> genre.add(eachFavGenre.getGenre()));
			
			final var result = movieRepository.getRecommendedMoviesWithGenre(genre);
			
			if(!CollectionUtils.isEmpty(result)) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public void deleteAllMovies() {
		final var allMovies = movieRepository.findAll();
		allMovies.forEach(eachMovie -> movieRepository.delete(eachMovie));
	}

}
