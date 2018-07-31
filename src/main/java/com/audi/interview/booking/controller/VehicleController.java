package com.audi.interview.booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> index() {
        log.debug("Getting all vehicles");
        return vehicleService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Vehicle createVehicle(@RequestBody @Valid Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }
}
