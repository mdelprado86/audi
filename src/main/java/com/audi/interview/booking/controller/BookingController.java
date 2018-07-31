package com.audi.interview.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.audi.interview.booking.jpa.domain.Booking;
import com.audi.interview.booking.service.BookingService;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Booking> index() {
        log.debug("Getting all bookings");
        return bookingService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Booking get(@PathVariable("id") Long id) {
        return bookingService.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Booking createBooking(@RequestParam(required = true) long userId,
            @RequestParam(required = true) long vehicleId) {
        return bookingService.createBooking(userId, vehicleId);
    }

    @RequestMapping(value = "/{id}/activate", method = RequestMethod.POST)
    public Booking activateBooking(@PathVariable("id") Long id) {
        return bookingService.activateBooking(id);
    }

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public Booking cancelBooking(@PathVariable("id") Long id) {
        return bookingService.cancelBooking(id);
    }
}
