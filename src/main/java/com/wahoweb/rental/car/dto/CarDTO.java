package com.wahoweb.rental.car.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CarDTO {
    private int carId;

    private String name;

    private String model;

    private String licenceNumber;

    private double rentPrice;

    private boolean available;

    private int numberOfPassanger;

    private String imageDir;

    private int mileage;

    private String fuelType;

    private String rating;

    private String transmission;

    private int numberOfDoors;

    private int numberOfSuitcases;

    private String description;
    private String videoUrl;

    private MultipartFile image;

    private byte[] returnedImage;
}
