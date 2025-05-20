package com.wahoweb.rental.car.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name ="admin")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
