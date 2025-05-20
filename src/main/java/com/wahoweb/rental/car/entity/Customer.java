package com.wahoweb.rental.car.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
