/**
 * 
 */
package com.fs.movie.service.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used for retrieving the data from the User.
 * This class should not be user for populating data to UI and for DB operations.
 *
 */
@NoArgsConstructor
@Data
public class UserModel {

	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	
	private List<String> genre;
}
