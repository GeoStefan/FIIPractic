package eu.ubis.fiimdb.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/*Mapare a tabelei Movies din baza de date*/

@Entity
@Table(schema = "fiimdb", name = "movie")
@NamedQueries ({
@NamedQuery(name="getAllMovies", query="SELECT m FROM MovieDao m"),
@NamedQuery(name="getMovieByName", query="SELECT m FROM MovieDao m WHERE m.name like ?1"),
@NamedQuery(name="getMovieByReleaseYear", query="SELECT m FROM MovieDao m WHERE extract(year from m.releaseDate) = ?1")
})
public class MovieDao {
	@Id
	@Column
	@SequenceGenerator(name="movie_seq", schema="fiimdb", sequenceName="movie_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
	private int id;
	
	@Column(name="RELEASE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseDate;
	
	private String name;
	
	private double rating;
	private int length;
	private String casting;
	private String director;
	private String description;
	private String writer;
	
	@ManyToMany
	@JoinTable(name = "movie_genre", 
		joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "ID"))
	private List<GenreDao> genres;
	
	public List<GenreDao> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDao> genres) {
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casting == null) ? 0 : casting.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + id;
		result = prime * result + length;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieDao other = (MovieDao) obj;
		if (casting == null) {
			if (other.casting != null)
				return false;
		} else if (!casting.equals(other.casting))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (id != other.id)
			return false;
		if (length != other.length)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
	
}
