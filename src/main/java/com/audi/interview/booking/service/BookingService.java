package com.audi.interview.booking.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.audi.interview.booking.exceptions.NotFoundException;
import com.audi.interview.booking.exceptions.VehicleAlreadyBookedException;
import com.audi.interview.booking.jpa.domain.Booking;
import com.audi.interview.booking.jpa.domain.Status;
import com.audi.interview.booking.jpa.domain.User;
import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.jpa.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    public Booking createBooking(long userId, long vehicleId) {
        Assert.notNull(userId, "User Id must not be null");
        Assert.notNull(vehicleId, "Vehicle Id must not be null");

        User user = userService.findOne(userId);
        Vehicle vehicle = vehicleService.findOne(vehicleId);
        Booking booking = createBooking(user, vehicle);

        return saveBooking(booking);
    }

    private Booking createBooking(User user, Vehicle vehicle) {
        Booking booking = new Booking(user, vehicle);

        long startDate = System.currentTimeMillis();
        long endDate = startDate + 24 * 60 * 60 * 1000;

        booking.setStart(new Date(startDate));
        booking.setEnd(new Date(endDate));

        return booking;
    }

    private synchronized Booking saveBooking(Booking booking) {
        if (!canBookingBeCreated(booking)) {
            throw new VehicleAlreadyBookedException("Vehicle was already booked");
        }

        return bookingRepository.saveAndFlush(booking);
    }

    private boolean canBookingBeCreated(Booking booking) {
        List<Status> statuses = Arrays.asList(Status.OPEN, Status.ACTIVE);
        List<Booking> bookings = bookingRepository.findByVehicleAndStatusIn(booking.getVehicle(), statuses);

        Predicate<Booking> predicateDatesOverlapped = b -> {
            long bookingStartTime = booking.getStart().getTime();
            long bookingEndTime = booking.getEnd().getTime();

            long bStartTime = b.getStart().getTime();
            long bEndTime = b.getEnd().getTime();

            return bookingStartTime <= bEndTime && bStartTime <= bookingEndTime;
        };

        Booking bookingExisting = bookings.stream().filter(predicateDatesOverlapped).findFirst().orElse(null);
        return bookingExisting == null;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findOne(Long id) {
        Assert.notNull(id, "Id must not be null");

        Booking booking = bookingRepository.findOne(id);
        if (booking == null) {
            throw new NotFoundException("Booking not found");
        }
        return booking;
    }

    public Booking activateBooking(Long id) {
        Assert.notNull(id, "Id must not be null");
        Booking booking = findOne(id);
        booking.setStatus(Status.ACTIVE);
        return bookingRepository.saveAndFlush(booking);
    }

    public Booking cancelBooking(Long id) {
        Assert.notNull(id, "Id must not be null");
        Booking booking = findOne(id);
        booking.setStatus(Status.CANCELLED);
        return bookingRepository.saveAndFlush(booking);
    }
}