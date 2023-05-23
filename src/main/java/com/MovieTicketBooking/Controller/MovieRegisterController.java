package com.MovieTicketBooking.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MovieTicketBooking.CustomException.BusinessException;
import com.MovieTicketBooking.CustomException.ControllerException;
import com.MovieTicketBooking.Entity.Movie;
import com.MovieTicketBooking.Service.MovieRegisterService;

@RestController
public class MovieRegisterController {
	
	private Logger logger = LoggerFactory.getLogger(MovieRegisterController.class);
	
	@Autowired
	private MovieRegisterService MovieService;
	
	
		
	@GetMapping("Movie/Registermovie")
	public ResponseEntity<?> BookMovie(@RequestBody Movie movie){
		try {
			Movie newmovie =MovieService.SaveMovie(movie);
			logger.info("A movie is registered with id {}",movie.getMovieId());
			return  new ResponseEntity<Movie>(newmovie,HttpStatus.CREATED);
		}
		catch (BusinessException e) {
			logger.error("ERROR OCCURED in BookMovie {}",e.getErrorMessage());
			ControllerException Ce = new ControllerException(
					e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED in BookMovie {}",e.getMessage());
			ControllerException Ce = new ControllerException("601", "BookMovie Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping("Movie/getAllMovies")
	public ResponseEntity<?> GetAllCinema() {
		try {
			List<Movie> allmovies = MovieService.GetAllMovie();
			return  new ResponseEntity<List<Movie>>(allmovies,HttpStatus.OK);
		}
		
		catch (BusinessException e) {
			logger.error("ERROR OCCURED in GetAllCinema {}",e.getMessage());
			ControllerException Ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED in GetAllCinema {}",e.getMessage());
			ControllerException Ce = new ControllerException("602", "GetAllCinema Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PutMapping("Movie/update")
	public ResponseEntity<?> UpdateMovieDetails(@RequestBody Movie movie) {
		
		try {
			Movie newmovie =MovieService.SaveMovie(movie);
			logger.info("A movie is updated with id {}",movie.getMovieId());
			return  new ResponseEntity<Movie>(newmovie,HttpStatus.CREATED);
		}
		
		catch (BusinessException e) {
			logger.error("ERROR OCCURED in UpdateMovieDetails {}",e.getErrorMessage());
			ControllerException Ce = new ControllerException(
					e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED in UpdateMovieDetails {}",e.getMessage());
			ControllerException Ce = new ControllerException("603", "BookMovie Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("Cinema/delete/booking/{MovieId}")	
	public ResponseEntity<?> DeleteMovie(@PathVariable long MovieId){
		
		try {
			MovieService.DeleteMovie(MovieId);
			logger.info("A  movie is deleted with id {}",MovieId);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		
		catch (IllegalArgumentException e) {
			logger.error("ERROR OCCURED in DeleteMovie {}",e.getMessage());
			throw new BusinessException("604", e.getMessage());
		}
	}
	

}
