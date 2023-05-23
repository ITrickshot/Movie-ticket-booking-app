package com.MovieTicketBooking.Service;

import java.util.List;

import com.MovieTicketBooking.Entity.Movie;

public interface MovieRegisterService {

	public Movie SaveMovie(Movie movie);
	
	public void DeleteMovie(long MovieId);
	
	public List<Movie> GetAllMovie();

}
