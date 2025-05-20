package com.wahoweb.rental.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
public class BookingDTO {
    private int bookingId;
    private Date bookStartDate;
    private Date bookEndDate;
    private int carId;
    private int customerId;
    private String customerEmail;
    private String userName;
    private String userPhone;
    private String pickLocation;
    private String endLocation;
    private double total;
}
