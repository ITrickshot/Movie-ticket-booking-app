package com.MovieTicketBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.MovieTicketBooking.Entity.CinemaIF;

public interface Cinemarepository extends CrudRepository<CinemaIF, Long>{
	
	@Query("select C from CinemaIF c")
	public List<CinemaIF>getAllCinema();
	
	
	
	
}
