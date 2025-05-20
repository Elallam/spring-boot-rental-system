package com.wahoweb.rental.car.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name ="reviews")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewId;
    private String comment;
    private double rating;
    private Date datePosted;
    private String userName;
    private String userImage;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
