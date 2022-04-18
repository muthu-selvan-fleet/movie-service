/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fs.movie.service.parser.CSVCell;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="GENRE")
@NoArgsConstructor
@Data
public class Genre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GENRE_ID")
	@CSVCell(label = "id")
	private long id;
	
	@Column(name="GENRE_VALUE")
	@NotNull(message = "Genre is mandatory")
	@CSVCell(label = "genre")
	private String genre;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "movie_id", nullable = true)
	@JsonIgnore
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	@JsonIgnore
	private User user;
}
