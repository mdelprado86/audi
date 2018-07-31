package com.audi.interview.booking.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.audi.interview.booking.jpa.domain.Booking;
import com.audi.interview.booking.jpa.domain.Status;
import com.audi.interview.booking.jpa.domain.Vehicle;

/**
 * For Spring Data JPA query methods see:
 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByVehicleAndStatusIn(Vehicle vehicle, List<Status> statuses);
}