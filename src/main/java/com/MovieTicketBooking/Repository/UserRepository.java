package com.MovieTicketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MovieTicketBooking.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
