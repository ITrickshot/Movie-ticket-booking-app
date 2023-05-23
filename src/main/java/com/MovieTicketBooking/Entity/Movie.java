package com.MovieTicketBooking.Entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;




@Entity
public class Movie implements Comparable<Movie>{
	
	@Id
	@GeneratedValue
	private long MovieId;
	private String MovieTitle;
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern="dd-mm-yyyy")
	private Date RelaeaseDate;
	private int ShowCycle;
	
	
	public long getMovieId() {
		return MovieId;
	}
	public void setMovieId(long movieId) {
		MovieId = movieId;
	}
	public String getMovieTitle() {
		return MovieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		MovieTitle = movieTitle;
	}
	public Date getRelaeaseDate() {
		return RelaeaseDate;
	}
	public void setRelaeaseDate(Date relaeaseDate) {
		RelaeaseDate = relaeaseDate;
	}
	public int getShowCycle() {
		return ShowCycle;
	}
	public void setShowCycle(int showCycle) {
		ShowCycle = showCycle;
	}
	public Movie(long movieId, String movieTitle, Date relaeaseDate, int showCycle) {
		super();
		MovieId = movieId;
		MovieTitle = movieTitle;
		RelaeaseDate = relaeaseDate;
		ShowCycle = showCycle;
	}
	public Movie() {
		
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(MovieId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return MovieId == other.MovieId;
	}
	
	
	@Override
	public int compareTo(Movie o) {
		
		return MovieId>o.MovieId?1:-1;
	}
	
	
	
	
	
	

}
