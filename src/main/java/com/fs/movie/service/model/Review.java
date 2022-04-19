/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="REVIEW")
@NoArgsConstructor
@Data
public class Review implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REVIEW_ID")
	private Long id;
	
	@Column(name="MOVIE_ID")
	@NotNull(message = "Movie Id is mandatory")
	private Long movieId;
	
	@Column(name="USER_ID")
	@NotNull(message = "User Id is mandatory")
	private Long userId;
	
	@Column(name="IS_UPVOTE")
	private String upvote;
	
	@Column(name="REVIEW")
	@NotNull(message = "Review is mandatory")
	private String review;
}
