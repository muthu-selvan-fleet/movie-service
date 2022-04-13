/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="MOVIE")
@Data
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MOVIE_ID")
	private long id;
	
	@Column(name="MOVIE_NAME")
	@NotNull(message = "Movie Name is mandatory")
	private String name;
	
	
	@Column(name="RELEASE_DATE")
	@NotNull(message = "Release Date is mandatory")
	private Date releaseDate;
	
	@OneToMany
	@JoinColumn(name = "REVIEW_ID")
	private List<Review> reviews;
	
	@OneToMany
	@JoinColumn(name = "GENRE_ID")
	private List<Genre> genre;
}
