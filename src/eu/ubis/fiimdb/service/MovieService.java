package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.sun.xml.bind.v2.runtime.reflect.ListIterator;

import eu.ubis.fiimdb.dao.GenreDao;
import eu.ubis.fiimdb.dao.MovieDao;
import eu.ubis.fiimdb.model.Movie;

/*MovieService realizeaza operatii de select, insert, delete si update asupra tabelelor. Initial folosind JDBC, apoi cu JPA*/

public class MovieService {
	private EntityManager entityManager;
	public MovieService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}

	public List<Movie> getMovies() {
		List<MovieDao> movieEntities = entityManager.createNamedQuery("getAllMovies", MovieDao.class).getResultList();
		List<Movie> movies = new ArrayList<Movie>();
		for (MovieDao movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}

	private Movie mapMovieEntityToModel(MovieDao movieEntity) {
		Movie movie = new Movie();

		movie.setId(movieEntity.getId());
		movie.setReleaseDate(movieEntity.getReleaseDate());
		movie.setName(movieEntity.getName());
		movie.setRating(movieEntity.getRating());
		movie.setLength(movieEntity.getLength());
		movie.setCasting(movieEntity.getCasting());
		movie.setDirector(movieEntity.getDirector());
		movie.setDescription(movieEntity.getDescription());
		movie.setWriter(movieEntity.getWriter());
	
		StringBuilder sb = new StringBuilder();
		List<GenreDao> genuri = movieEntity.getGenres();
		for(GenreDao g : genuri)
			sb.append(g.toString());
		movie.setGenre(sb.toString());
		return movie;
	}

	public Movie getMovieById(int id) {
		List<MovieDao> movieEntities = entityManager.createNamedQuery("getAllMovies", MovieDao.class).getResultList();
		Movie movie = new Movie();
		for (MovieDao movieEntity : movieEntities)
			if(movieEntity.getId() == id) {
				movie = mapMovieEntityToModel(movieEntity);
				break;
			}
		return movie;
	}
	
	public List<Movie> search(String criteria, String value) {
		List<Movie> movies = new ArrayList<Movie>();
		List<MovieDao> movieEntities = new ArrayList<MovieDao>();
		if(criteria.equals("name")) {
			TypedQuery<MovieDao> tquery = entityManager.createNamedQuery("getMovieByName", MovieDao.class);
			tquery.setParameter(1, "%"+value+"%");
			movieEntities = tquery.getResultList();
		}
		if(criteria.equals("genre")) {
			TypedQuery<MovieDao> tquery = entityManager.createNamedQuery("getAllMovies", MovieDao.class);
			movieEntities = tquery.getResultList();
			Object it = movieEntities.listIterator();
			while(((java.util.ListIterator<MovieDao>) it).hasNext()) {
				List<GenreDao> genres = new ArrayList<>();
				genres = ((java.util.ListIterator<MovieDao>) it).next().getGenres();
				boolean find = false;
				for(GenreDao genre:genres)
					if(genre.getName().equals(value))
						find = true;
				if(!find)		
					((java.util.ListIterator<MovieDao>) it).remove();
			}
		}
		if(criteria.equals("year")){
			TypedQuery<MovieDao> tquery = entityManager.createNamedQuery("getMovieByReleaseYear", MovieDao.class);
			tquery.setParameter(1, Integer.parseInt(value));
			movieEntities = tquery.getResultList();
		}
		for (MovieDao movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}
	
	public boolean updateMovie(Movie movie, int id) {
		
		MovieDao movieDao = new MovieDao();
		try {
			entityManager.getTransaction().begin();
			movieDao = entityManager.find(MovieDao.class, id);
			
			movieDao.setName(movie.getName());
			movieDao.setCasting(movie.getCasting());
			movieDao.setDescription(movie.getDescription());
			movieDao.setLength(movie.getLength());
			movieDao.setRating(movie.getRating());
			movieDao.setWriter(movie.getWriter());
			movieDao.setDirector(movie.getDirector());
			
			entityManager.getTransaction().commit();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		finally {
			//entityManager.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getMovies(int pageNumber, int pageSize) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<Movie> movies = new ArrayList<Movie>();
		List<MovieDao> moviesDao = new ArrayList<MovieDao>();

		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("Select m From MovieDao m");
		query.setFirstResult((pageNumber-1) * pageSize);
		query.setMaxResults(pageSize);

		moviesDao = query.getResultList();
		entityManager.close();
		emf.close();
		for(MovieDao movie : moviesDao)
			movies.add(mapMovieDaoToMovie(movie));
		return movies;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getMoviesNoPagination() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<Movie> movies = new ArrayList<Movie>();
		List<MovieDao> moviesDao = new ArrayList<MovieDao>();
	
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("Select m From MovieDao m");
		moviesDao = query.getResultList();
		entityManager.close();
		emf.close();
		for(MovieDao movie : moviesDao)
			movies.add(mapMovieDaoToMovie(movie));
		return movies;
	}

	//DELETE WITH JPA
	public void deleteMovie(int movieId) {
		MovieDao movieDao = new MovieDao();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		
		entityManager.getTransaction().begin();
		movieDao = entityManager.find(MovieDao.class, movieId);
		entityManager.remove(movieDao);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	//UPDATE WITH JPA
	public void updateMovie(Movie movie, int[] movieGenreIds, int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		MovieDao movieDao = new MovieDao();
		
		entityManager.getTransaction().begin();
		movieDao = entityManager.find(MovieDao.class, id);
		
		movieDao.setName(movie.getName());
		movieDao.setCasting(movie.getCasting());
		movieDao.setDescription(movie.getDescription());
		movieDao.setLength(movie.getLength());
		movieDao.setRating(movie.getRating());
		movieDao.setWriter(movie.getWriter());
		movieDao.setDirector(movie.getDirector());
		List<GenreDao> movieGenres = new ArrayList<>();
		for(int movieGenreId : movieGenreIds) {
			GenreDao movieGenre = new GenreDao();
			movieGenre.setId(movieGenreId);
			movieGenres.add(movieGenre);
		}
		movieDao.setGenres(movieGenres);		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	//INSERT WITH JPA
	public boolean insertMovie(Movie movie, int[] movieGenreIds) {
		MovieDao movieDao = mapMovieModelToDao(movie);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<GenreDao> movieGenres = new ArrayList<>();
		for(int movieGenreId : movieGenreIds) {
			GenreDao movieGenre = new GenreDao();
			movieGenre.setId(movieGenreId);
			movieGenres.add(movieGenre);
		}
		movieDao.setGenres(movieGenres);
		
		entityManager.getTransaction().begin();
		entityManager.persist(movieDao);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
		return true;
	}
	
	private MovieDao mapMovieModelToDao(Movie movie) {
		MovieDao movieDao = new MovieDao();
		movieDao.setId(movie.getId());
		movieDao.setReleaseDate(movie.getReleaseDate());
		movieDao.setName(movie.getName());
		movieDao.setRating(movie.getRating());
		movieDao.setLength(movie.getLength());
		movieDao.setCasting(movie.getCasting());
		movieDao.setDirector(movie.getDirector());
		movieDao.setDescription(movie.getDescription());
		movieDao.setWriter(movie.getWriter());		
		return movieDao;
	}
	
	private Movie mapMovieDaoToMovie(MovieDao movieDao) {
		Movie movie = new Movie();
		movie.setId(movieDao.getId());
		movie.setReleaseDate(movieDao.getReleaseDate());
		movie.setName(movieDao.getName());
		movie.setRating(movieDao.getRating());
		movie.setLength(movieDao.getLength());
		movie.setCasting(movieDao.getCasting());
		movie.setDirector(movieDao.getDirector());
		movie.setWriter(movieDao.getWriter());
		if (movieDao.getDescription() != null) {
			movie.setDescription(movieDao.getDescription());
		} else {
			movie.setDescription("");
		}
		
		String genres = mapGenreEntityListToMovie(movieDao.getGenres());
		movie.setGenre(genres);
		return movie;
	}
	
	private String mapGenreEntityListToMovie(List<GenreDao> genreDao) {
		if (genreDao.size() <= 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (GenreDao ge : genreDao) {
			stringBuilder.append(ge.getName());
			if (genreDao.indexOf(ge) != genreDao.size() - 1) {
				stringBuilder.append(", ");
			}
		}
		return stringBuilder.toString();
	}
	
}
