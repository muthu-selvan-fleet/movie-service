/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="USER")
@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany
	@JoinColumn(name = "GENRE_ID")
	private List<Genre> favouriteGenre;
	
	@OneToMany
	@JoinColumn(name = "REVIEW_ID")
	private List<Review> reviews;
}
