/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.NotNull;

import com.fs.movie.service.parser.CSVCell;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fs_ms
 *
 */

@Entity
@Table(name="MOVIES")
@NoArgsConstructor
@Data
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MOVIE_ID")
	@CSVCell(label = "id")
	private Long id;
	
	@Column(name="MOVIE_NAME")
	@NotNull(message = "Movie Name is mandatory")
	@CSVCell(label = "Movie Name")
	private String name;
	
	
	@Column(name="RELEASE_DATE")
	@NotNull(message = "Release Date is mandatory")
	@CSVCell(label = "Release Date")
	private Date releaseDate;

	@Column(name="UPVOTE_COUNT")
	private Long upVoteCount;

	@Column(name="DOWNVOTE_COUNT")
	private Long downVoteCount;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY,
			cascade = CascadeType.REFRESH)
	private List<Genre> genre;
	
	public Movie(String name, Date releaseDate,long upVoteCount, long downVoteCount) {
		this.name = name;
		this.releaseDate = releaseDate;
		this.upVoteCount = upVoteCount;
		this.downVoteCount = downVoteCount;
	}
}
