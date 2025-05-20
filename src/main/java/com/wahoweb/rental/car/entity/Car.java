package com.wahoweb.rental.car.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;

    private String name;

    private String model;

    private String licenceNumber;

    private double rentPrice;

    private boolean available;

    private int numberOfPassanger;

    private String imageDir;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;


    private int mileage;

    private String fuelType;

    private String rating;

    private String transmission;

    private String carType;

    private int numberOfDoors;

    private int numberOfSuitcases;

    @Lob
    @Column(name = "description", length = 10000)
    private String description;

    private String videoUrl;


}
