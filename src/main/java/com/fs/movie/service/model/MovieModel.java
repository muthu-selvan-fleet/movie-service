/**
 * 
 */
package com.fs.movie.service.model;

import java.io.Serializable;
import java.util.List;

import com.fs.movie.service.parser.CSVCell;

import lombok.Data;
import lombok.NoArgsConstructor;

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
	private long id;
	
	@CSVCell(label = "Movie Name")
	private String name;
	
	@CSVCell(label = "Release Date")
	private String releaseDate;
	
	private List<String> genre;
}
