package com.audi.interview.booking.jpa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Booking extends AbstractPersistable {

    Date start;

    Date end;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    User user;

    @ManyToOne
    Vehicle vehicle;

    public Booking() {
    }

    public Booking(User user, Vehicle vehicle) {
        this.user = user;
        this.vehicle = vehicle;
        this.status = Status.OPEN;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
