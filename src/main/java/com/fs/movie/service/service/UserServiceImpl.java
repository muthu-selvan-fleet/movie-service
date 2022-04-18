/**
 * 
 */
package com.fs.movie.service.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.User;
import com.fs.movie.service.repository.GenreRepository;
import com.fs.movie.service.repository.UserRepository;

/**
 * @author fs_ms
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		final List<User> users = userRepository.findAll();
		
		return CollectionUtils.isEmpty(users) ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
					new ResponseEntity<>(users, HttpStatus.OK)
			;
	}

	@Override
	public ResponseEntity<User> addUser(final User user) {
		try {
			final var newUser = new User(user.getFirstName(),user.getLastName(),
					user.getEmailId(), user.getPhoneNumber());
			userRepository.save(newUser);
			user.getFavouriteGenre().forEach(eachGenre -> {
				eachGenre.setUser(newUser);
				genreRepository.save(eachGenre);
			});
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<User> updateUser(final long userId, final User inputUser) {
		final var userOptional = userRepository.findById(userId);
	    
	    if(userOptional.isPresent()) {
	    	final var user = userOptional.get();
	    	user.setFirstName(inputUser.getFirstName());
	    	user.setLastName(inputUser.getLastName());
	    	user.setEmailId(inputUser.getEmailId());
	    	user.setPhoneNumber(inputUser.getPhoneNumber());
	    	
//	    	Update genre
	    	user.getFavouriteGenre().forEach(eachExistingGenre -> genreRepository.delete(eachExistingGenre));
	    	inputUser.getFavouriteGenre().forEach(eachExistingGenre -> { 
	    		eachExistingGenre.setUser(user);
	    		genreRepository.save(eachExistingGenre);
	    	});
	    	
	    	user.setFavouriteGenre(inputUser.getFavouriteGenre());
	    	
//	    	Update user
	    	userRepository.save(user);
	    	return new ResponseEntity<>(user, HttpStatus.OK);
	    }
	    
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public void deleteAllUsers() {
		final var allUsers = userRepository.findAll();
		allUsers.forEach(eachUser -> userRepository.delete(eachUser));
	}
}
