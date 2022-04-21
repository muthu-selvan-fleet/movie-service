/**
 * 
 */
package com.fs.movie.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.User;
import com.fs.movie.service.model.UserModel;
import com.fs.movie.service.service.UserService;

/**
 * @author fs_ms
 *
 */
@RestController
@CrossOrigin(value = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	// @PostMapping("/register")
	public ResponseEntity<User> addUser(@RequestBody final UserModel user) {
		return userService.addUser(prepareUserObject(user));
	}
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") final long userId, 
			@RequestBody final UserModel user) {
		return userService.updateUser(userId,prepareUserObject(user));
	}
	
	private User prepareUserObject(final UserModel userModel) {
		final var user = new User();
		final var favGenre = new ArrayList<Genre>();
		
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setEmailId(userModel.getEmailId());
		user.setPhoneNumber(userModel.getPhoneNumber());
		
		userModel.getGenre().forEach(eachGenre -> {
			var genre = new Genre();
			genre.setGenre(eachGenre);
			favGenre.add(genre);
		});
		
		user.setFavouriteGenre(favGenre);
		return user;
	}
}
