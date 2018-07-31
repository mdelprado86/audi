package com.audi.interview.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.audi.interview.booking.exceptions.NotFoundException;
import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.jpa.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        Assert.notNull(vehicle, "Vehicle must not be null");
        return vehicleRepository.saveAndFlush(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findOne(Long id) {
        Assert.notNull(id, "Id must not be null");

        Vehicle vehicle = vehicleRepository.findOne(id);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found");
        }
        return vehicle;
    }

    public Vehicle findByLicensePlate(String licensePlate) {
        Assert.hasLength(licensePlate, "License plaete must not be empty");
        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
