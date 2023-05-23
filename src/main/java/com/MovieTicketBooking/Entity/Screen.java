package com.MovieTicketBooking.Entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Screen implements Comparable<Screen> {
	@Id
	@GeneratedValue
	private long ScreenId;
	private String Screentype;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_movieId")
	private Movie movie;

	public long getScreenId() {
		return ScreenId;
	}

	public void setScreenId(long screenId) {
		ScreenId = screenId;
	}

	public String getScreentype() {
		return Screentype;
	}

	public void setScreentype(String screentype) {
		Screentype = screentype;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Screen(long screenId, String screentype, Movie movie) {
		super();
		ScreenId = screenId;
		Screentype = screentype;
		this.movie = movie;
	}

	public Screen() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(ScreenId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screen other = (Screen) obj;
		return ScreenId == other.ScreenId;
	}

	@Override
	public int compareTo(Screen o) {
		
		return ScreenId>o.ScreenId?1:-1;
	}
	
	

}
