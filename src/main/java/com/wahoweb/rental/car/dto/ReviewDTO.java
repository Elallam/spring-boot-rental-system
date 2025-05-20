package com.wahoweb.rental.car.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private int reviewId;
    private String comment;
    private double rating;
    private Date datePosted;
    private int carId;
    private int userId;
    private String userName;
    private String userImage;
}
