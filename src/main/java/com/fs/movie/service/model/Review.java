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

import lombok.Data;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="REVIEW")
@Data
public class Review implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REVIEW_ID")
	private long id;
	
	@Column(name="MOVIE_ID")
	private long movieId;
	
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="IS_UPVOTE")
	private boolean upvote;
	
	@Column(name="REVIEW")
	private String review;
}
