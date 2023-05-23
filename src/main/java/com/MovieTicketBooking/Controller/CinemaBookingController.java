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
import com.MovieTicketBooking.Entity.CinemaIF;
import com.MovieTicketBooking.Entity.User;
import com.MovieTicketBooking.Service.MoviebookingService;
import com.MovieTicketBooking.CustomException.BusinessException;
import com.MovieTicketBooking.CustomException.ControllerException;

@RestController
public class CinemaBookingController {
	
	private Logger logger = LoggerFactory.getLogger(CinemaBookingController.class);
	
	@Autowired
	private MoviebookingService moviebookingrepo;
	
	
	/* user can create a booking
	 * @param user
	*/	
	@GetMapping("Cinema/Bookmovie")
	public ResponseEntity<?> BookMovie(@RequestBody User user){
		try {
			logger.info("A moviebooking is registered with id {}",user.getUserId());
			User newUser = moviebookingrepo.BookAMovie(user);
			return  new ResponseEntity<User>(newUser,HttpStatus.CREATED);
			
		}
		
		catch (BusinessException e) {
			logger.error("ERROR OCCURED {}",e.getErrorMessage());
			ControllerException Ce = new ControllerException(
					e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException("801", "BookMovie Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* user can cancel a booking
	*/
	
	@DeleteMapping("Cinema/delete/booking/{UserId}")	
	public ResponseEntity<?> Cancelbooking(@PathVariable long UserId){
		try {
			
			moviebookingrepo.CancelBookingbyId(UserId);
			logger.info("A booked movie is cancelled with id {}",UserId);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}	
		catch (IllegalArgumentException e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			throw new BusinessException("802", e.getMessage());
		}
		
	}
	
	/* user can update a booking
	*/
	
	@PutMapping("Cinema/update")
	public ResponseEntity<?> UpdateBooking(@RequestBody User user) {
		
		try {
			User newUser = moviebookingrepo.BookAMovie(user);
			logger.info("A moviebooking is updated with id {}",user.getUserId());
			return  new ResponseEntity<User>(newUser,HttpStatus.CREATED);			
		}
		catch (BusinessException e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException(
					e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException("803", "UpdateBooking Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* user can view all bookings based on id
	*/
	
	@GetMapping("Cinema/AllBookingsbyUser/{UserId}")
	public ResponseEntity<?> GetAllBookings(@PathVariable  long UserId) {
		try {
			List<CinemaIF> bookings = moviebookingrepo.getAllBookingsById(UserId);
			logger.info("movie list revoked with id {}",UserId);
			return new ResponseEntity<List<CinemaIF>>(bookings,HttpStatus.OK);
		}
		catch (BusinessException e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException("804", "GetAllBookings Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	/* user can view  bookings based on hallname
	*/
	
	@GetMapping("Cinema/AllBookingsbyUser/{UserId}/HallName/{HallName}")
	public ResponseEntity<?> GetAllBookingsByHallName(@PathVariable  long UserId ,@PathVariable  String HallName) {
		try {
			List<CinemaIF> bookings =moviebookingrepo.getCinemaByHall(UserId,HallName);
			return new ResponseEntity<List<CinemaIF>>(bookings,HttpStatus.OK);
		}
			
		catch (BusinessException e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("ERROR OCCURED {}",e.getMessage());
			ControllerException Ce = new ControllerException("805", "GetAllBookings Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	/* user can view  all the cinemas available in hall with the screen  */
	
	@GetMapping("Cinema/getAllCinema")
	public ResponseEntity<?> GetAllCinema() {
		try {
			List<CinemaIF> cinemas = moviebookingrepo.getAllCinema();
			return new ResponseEntity<List<CinemaIF>>(cinemas,HttpStatus.OK);
		}
		
		
		catch (BusinessException e) {
			logger.error(e.getErrorMessage());
			ControllerException Ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ControllerException Ce = new ControllerException("806", "GetAllBookings Controller Exception Occures");
			return new ResponseEntity<ControllerException>(Ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
