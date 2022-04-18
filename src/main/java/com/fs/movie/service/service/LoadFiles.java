package com.fs.movie.service.service;

import com.fs.movie.service.constants.Constants;
import com.fs.movie.service.model.Genre;
import com.fs.movie.service.model.Movie;
import com.fs.movie.service.model.MovieModel;
import com.fs.movie.service.parser.CSVParser;
import com.fs.movie.service.repository.GenreRepository;
import com.fs.movie.service.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;


@Repository
@ComponentScan(basePackages = {"com.restuarant.rest.webservices.restuarant.*"})
@PropertySource("classpath:application.properties")
public class LoadFiles {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private Environment env;

	public LoadFiles() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init() {
		boolean isRestore = false;
		
		try {
			isRestore = Boolean.parseBoolean(env.getProperty(Constants.RESTORE.getValue()));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Restore value : "+isRestore);
			
			if(!isRestore) {
				return;
			}
			
//			Processing Genre
			{
				genreService.deleteAllGenre();
				Resource resource = resourceLoader.getResource("classpath:"+"assets/csv/Genre.csv");
				List<Genre> genreLst = CSVParser.readCSVFile(Genre.class, resource.getFile().getAbsolutePath());
				
				genreLst.forEach(eachGenre -> genreRepository.save(eachGenre));
				System.out.println("********************** Genre Processed ***********");
			}

			// Delete all reviews
			reviewService.deleteAllReviews();
			
			// Process Movies
			{
				movieService.deleteAllMovies();
				Resource resource = resourceLoader.getResource("classpath:"+"assets/csv/MovieModel.csv");
				List<MovieModel> movieModels = CSVParser.readCSVFile(MovieModel.class, resource.getFile().getAbsolutePath());
				movieModels.forEach(eachMovieModel -> {
					final var movie = new Movie(eachMovieModel.getName(),eachMovieModel.getDate(),eachMovieModel.getUpVoteCount(),eachMovieModel.getDownVoteCount());
					movieRepository.save(movie);

					eachMovieModel.getGenreInList().forEach(eachGenre -> {
						eachGenre.setMovie(movie);
						genreRepository.save(eachGenre);
					});

				});
				
				System.out.println("********************** Movie Processed ***********");
			}
			
			// Process User
			{
				userService.deleteAllUsers();
				System.out.println("********************** User Processed ***********");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}