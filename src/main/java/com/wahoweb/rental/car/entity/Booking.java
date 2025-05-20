package com.wahoweb.rental.car.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private Date bookStartDate;
    private Date bookEndDate;
    private String customerEmail;
    private String userName;
    private String userPhone;
    private String pickLocation;
    private String endLocation;
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
