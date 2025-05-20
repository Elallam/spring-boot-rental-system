package com.wahoweb.rental.car.controller;


import com.wahoweb.rental.car.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String login;
    private String password ;
    private String address;
    private String phoneNumber;

    private Role role;

}
