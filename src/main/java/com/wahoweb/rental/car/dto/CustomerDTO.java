package com.wahoweb.rental.car.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private int customerId;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String phoneNumber;
}
