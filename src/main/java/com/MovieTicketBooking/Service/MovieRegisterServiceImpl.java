package com.MovieTicketBooking.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MovieTicketBooking.CustomException.BusinessException;
import com.MovieTicketBooking.Entity.Movie;
import com.MovieTicketBooking.Repository.MovieRepository;


@Service
@Transactional
public class MovieRegisterServiceImpl implements MovieRegisterService{
	
	@Autowired
	private MovieRepository movierepo;

	@Override
	public Movie SaveMovie(Movie movie) {
		try {
			if(movie.getMovieTitle().isEmpty()) {
				throw new IllegalArgumentException();
			}
			
			Movie newmovie =movierepo.save(movie);
			return newmovie;
		}
		catch(IllegalArgumentException e) {
			throw new BusinessException("501", "Null value can not be inserted in movie "+e.getMessage());
		}
		catch(Exception e) {
			throw new BusinessException("502", "Server Failure "+e.getMessage());
		}
		
	}


	@Override
	public void DeleteMovie(long MovieId) {
		try {
			if(MovieId<0) {
				throw new IllegalArgumentException();
			}
			movierepo.deleteById(MovieId);
		}
		catch(IllegalArgumentException e) {
			throw new BusinessException("503", e.getMessage());
		}
				
	}

	@Override
	public List<Movie> GetAllMovie() {
		
		try {
			List<Movie> movies = movierepo.findAll();
			if(movies.isEmpty()) {
				throw new IllegalArgumentException();
			}
			return movies;
		}
		
		catch(Exception e){
			throw new BusinessException("504", "Server Failure "+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	

	
}
