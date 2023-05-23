package com.MovieTicketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MovieTicketBooking.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
