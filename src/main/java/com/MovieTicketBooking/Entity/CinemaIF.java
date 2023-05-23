package com.MovieTicketBooking.Entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CinemaIF implements Comparable<CinemaIF> {
	@Id
	@GeneratedValue
	@Column(name="cinema_id")
	private long CinemaID;
	
	private String Hallname;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_screenId",referencedColumnName = "cinema_id")
	private List<Screen> screens;

	public long getCinemaID() {
		return CinemaID;
	}

	public void setCinemaID(long cinemaID) {
		CinemaID = cinemaID;
	}

	public String getHallname() {
		return Hallname;
	}

	public void setHallname(String hallname) {
		Hallname = hallname;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}

	public CinemaIF(long cinemaID, String hallname, List<Screen> screens) {
		super();
		CinemaID = cinemaID;
		Hallname = hallname;
		this.screens = screens;
	}

	public CinemaIF() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	@Override
	public int hashCode() {
		return Objects.hash(CinemaID, Hallname, screens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CinemaIF other = (CinemaIF) obj;
		return CinemaID == other.CinemaID && Objects.equals(Hallname, other.Hallname)
				&& Objects.equals(screens, other.screens);
	}

	@Override
	public int compareTo(CinemaIF o) {
				return CinemaID>o.CinemaID?1:-1;
	}
	
	
	
	
	
	
	

}
