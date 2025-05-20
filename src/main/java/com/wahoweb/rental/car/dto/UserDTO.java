package com.wahoweb.rental.car.dto;

import com.wahoweb.rental.car.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String image;
    private Role role;
    private boolean verified=false;

}
