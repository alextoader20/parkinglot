package com.parking.parkinglot2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CarPhoto {
    private Long id;

    String filename;

    String filetype;

    byte[] fileContent;

    Car car;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @OneToOne
    public Car getCar() { return car; }




}
