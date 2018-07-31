package com.audi.interview.booking.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
public class Vehicle extends AbstractPersistable {

    @NotNull(message = "License plate is a required field")
    @Column(unique = true)
    private String licensePlate;

    @NotNull(message = "Vin is a required field")
    private String vin;

    @NotNull(message = "Model is a required field")
    private String model;

    @NotNull(message = "Active is a required field")
    private Boolean active;

    @NotNull(message = "Color is a required field")
    private String color;

    @NotNull(message = "Valid till is a required field")
    @Future(message = "Valid till must be a data in the future")
    private Date validTill;

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    public Boolean getActive() {
        return active;
    }

    public String getColor() {
        return color;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }
}
