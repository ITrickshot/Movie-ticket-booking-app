package com.MovieTicketBooking.Service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MovieTicketBooking.Entity.CinemaIF;
import com.MovieTicketBooking.Entity.User;
import com.MovieTicketBooking.Repository.Cinemarepository;
import com.MovieTicketBooking.Repository.UserRepository;
import com.MovieTicketBooking.CustomException.BusinessException;


@Service
public class MovieBookingServiceImpl implements MoviebookingService {
	
	@Autowired
	private UserRepository UserRepo;
	
	@Autowired
	private Cinemarepository CineRepo; 

	
	
	
	@Override
	@Transactional
	public User BookAMovie(User user) {
		try {
			if(user.getUserId()>(long)0) {
				throw new IllegalArgumentException();
			}
			User newUser =UserRepo.save(user);
			return newUser;
		}
		
		catch(IllegalArgumentException e) {
			throw new BusinessException("701", "Null value can not be inserted "+e.getMessage());
		}
		catch(Exception e) {
			throw new BusinessException("702", "Server Failure "+e.getMessage());
		}
			
	}




	@Override
	@Transactional
	public void CancelBookingbyId(long UserId) {
		try {
			if(UserId<0) {
				throw new IllegalArgumentException();
			}
			UserRepo.deleteById(UserId);
		}
		catch(IllegalArgumentException e) {
			throw new BusinessException("703", e.getMessage());
		}
	}


	@Override
	@Transactional
	public List<CinemaIF> getAllBookingsById(long UserId) {
		try {
			if(UserId<0) {
				throw new IllegalArgumentException();
			}
			User newUser = UserRepo.getById(UserId)	;
			if(newUser.getCinemas().isEmpty()) {
				throw new BusinessException("704", "No Booking by the user so far ");		
			}
			return newUser.getCinemas();
		}
		catch(Exception e){
			throw new BusinessException("705", "Server Failure "+e.getMessage());
		}
		
	}


	@Override
	@Transactional
	public List<CinemaIF> getAllCinema() {
		try {
			List<CinemaIF> cinemas = CineRepo.getAllCinema();
			if(cinemas.isEmpty()) {
				throw new BusinessException("706", "No cinema is running on theatre ");
			}
			return cinemas;
		}
		catch(Exception e){
			throw new BusinessException("707", "Server Failure "+e.getMessage());
		}
		
	}




	@Override
	@Transactional
	public List<CinemaIF> getCinemaByHall(long userId, String hallName) {
		try {
			User newUser = UserRepo.getById(userId)	;
			if(newUser.getCinemas().isEmpty()) {
				throw new BusinessException("704", "No Booking by the user so far ");		
			}
			List<CinemaIF> cinemas = newUser.getCinemas();
			List<CinemaIF> newcinema = newUser.getCinemas();
			for(CinemaIF C :cinemas) {
				if(C.getHallname().equals(hallName)) {
					newcinema.add(C);
				}	
			}
			return newcinema;
		}
		
		catch(Exception e){
			throw new BusinessException("705", "Server Failure "+e.getMessage());
		}	
		
	}
	

}
