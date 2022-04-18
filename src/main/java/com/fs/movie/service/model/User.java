/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private long id;
	
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
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = CascadeType.REFRESH)
	private List<Genre> favouriteGenre;
	
	
	public User(String firstName,String lastName,
			String emailId, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}
}
