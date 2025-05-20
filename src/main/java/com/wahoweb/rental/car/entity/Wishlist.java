package com.wahoweb.rental.car.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name ="wishlist")
@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishlistId;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
