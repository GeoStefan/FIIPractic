package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;
/*MovieBean incapsuleaza liste de Movie, returnate la cautarea filmelor dupa anumite cirterii
 */
public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();
	private List<Movie> movies = new ArrayList<Movie>();
	private Movie movie = new Movie();
	
	public void getAllMovies() {
		movies = movieService.getMovies();
	}
	
	public void getMovieById(int id) {
		movie = movieService.getMovieById(id);
	}

	public void getSearchedMovie(String criteria, String value) {
		movies = movieService.search(criteria, value);
	}

	public List<Movie> getMovies() {
		return movies;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public Movie getSingleMovie(int id) {
		return movieService.getMovieById(id);
	}
	
	public boolean updateMovie(Movie movie,int idMovie) {
		return movieService.updateMovie(movie, idMovie);
	}
	
	public boolean insertMovie(Movie movie, int[] movieGenreIds) {
		return movieService.insertMovie(movie, movieGenreIds);
	}
	
	public void deleteMovie(int movieId) {
		movieService.deleteMovie(movieId);
	}
}
