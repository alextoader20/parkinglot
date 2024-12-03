package com.parking.parkinglot2.common;

public class UserDto {
    private String username;
    private String email;
    private long id;

    // Constructor
    public UserDto(String username, String email, long id) {
        this.username = username;
        this.email = email;
        this.id=id;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }
}