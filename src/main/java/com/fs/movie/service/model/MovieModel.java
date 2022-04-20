/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fs.movie.service.parser.CSVCell;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

/**
 * This class is used for reading the data from CSV file and getting data from UI only.
 * It should not be used for DB Operation or UI data population
 *
 */
@NoArgsConstructor
@Data
public class MovieModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CSVCell(label = "id")
	private Long id;
	
	@CSVCell(label = "Movie Name")
	private String name;
	
	@CSVCell(label = "Release Date")
	private String releaseDate;

	@CSVCell(label = "Upvotes")
	private Long upVoteCount;

	@CSVCell(label = "Downvotes")
	private Long downVoteCount;

	@CSVCell(label = "Genre")
	@Transient
	private String genreFromInput;

	private List<String> genre;

	public Date getDate() {
		try {
			if(this.releaseDate != null && !this.releaseDate.isBlank()) {
				return new SimpleDateFormat("dd-MM-yyyy").parse(this.releaseDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Genre> getGenreInList() {
		final var result = new ArrayList<Genre>();

		if(this.genreFromInput != null && !this.genreFromInput.isBlank()) {
			final var delimiter = this.genreFromInput.contains("@~@") ? "@~@" : ",";
			final var genereLst = Arrays.asList(this.genreFromInput.split(delimiter));
			genereLst.forEach(eachGenreStr -> {
				final var localgenre = new Genre();
				localgenre.setGenre(eachGenreStr);
				result.add(localgenre);
			});
			return result;
		}
		return null;
	}
}
