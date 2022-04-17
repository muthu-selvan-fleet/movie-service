/**
 * 
 */
package com.fs.movie.service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fs.movie.service.model.User;

/**
 * @author fs_ms
 *
 */
public interface UserService {

	ResponseEntity<List<User>> getAllUsers();

	ResponseEntity<User> addUser(final User user);

	ResponseEntity<User> updateUser(final long userId, final User user);
	
	void deleteAllUsers();
}
