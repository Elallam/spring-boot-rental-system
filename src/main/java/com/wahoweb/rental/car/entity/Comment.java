package com.wahoweb.rental.car.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    private int id;
    private String email;
    private String tele;
    private String nom;
    private String comment;
}
