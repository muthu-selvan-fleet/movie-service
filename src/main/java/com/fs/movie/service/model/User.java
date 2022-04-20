/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fs.movie.service.parser.CSVCell;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="USERS")
@NoArgsConstructor
@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	@CSVCell(label = "id")
	private Long id;
	
	@Column(name="FIRST_NAME")
	@CSVCell(label = "First Name")
	private String firstName;
	
	@Column(name="LAST_NAME")
	@CSVCell(label = "Last Name")
	private String lastName;
	
	@Column(name="EMAIL_ID")
	@CSVCell(label = "Email Id")
	private String emailId;
	
	@Column(name="PHONE_NUMBER")
	@CSVCell(label = "Phone Number")
	private String phoneNumber;

	@Column(name="PASSWORD")
	@CSVCell(label = "password")
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = CascadeType.REFRESH)
	private List<Genre> favouriteGenre;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(  name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private Set<Role> roles = new HashSet<>();
	
	public User(String firstName,String lastName,
			String emailId, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
	}
}
