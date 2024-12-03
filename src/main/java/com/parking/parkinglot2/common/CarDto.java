package com.parking.parkinglot2.common;

public class CarDto {

    private long id;
    private String licensePlate;
    private String parkingSpot;
    private String ownerName;

    public CarDto(Long id, String licensePlate, String parkingSpot, String ownerName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public String getOwnerName() {
        return ownerName;
    }

}
