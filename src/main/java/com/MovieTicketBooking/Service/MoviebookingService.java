package com.MovieTicketBooking.Service;

import java.util.List;

import com.MovieTicketBooking.Entity.CinemaIF;
import com.MovieTicketBooking.Entity.User;

public interface MoviebookingService {

	 User BookAMovie(User user);

    void CancelBookingbyId(long UserId);

    List<CinemaIF> getAllBookingsById(long UserId);

	List<CinemaIF> getAllCinema();

	List<CinemaIF> getCinemaByHall(long userId, String hallName);

}
