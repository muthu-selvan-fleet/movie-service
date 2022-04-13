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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="GENRE")
@Data
public class Genre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GENRE_ID")
	private long id;
	
	@Column(name="GENRE")
	@NotNull(message = "Genre is mandatory")
	private String genre;
}
