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
public class User implements Comparable<User> {
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private long UserId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_CinemaId" ,referencedColumnName="user_id")
	private List<CinemaIF> cinemas;

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public List<CinemaIF> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<CinemaIF> cinemas) {
		this.cinemas = cinemas;
	}

	public User(long userId, List<CinemaIF> cinemas) {
		super();
		UserId = userId;
		this.cinemas = cinemas;
	}

	public User() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(UserId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return UserId == other.UserId;
	}

	@Override
	public int compareTo(User o) {
		
		return UserId>o.UserId?1:-1;
	}
	
	
	
	
	

}
