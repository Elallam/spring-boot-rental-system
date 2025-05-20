package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.controller.AuthenticationRequest;
import com.wahoweb.rental.car.controller.AuthenticationResponse;
import com.wahoweb.rental.car.controller.RegisterRequest;
import com.wahoweb.rental.car.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public List<User> getUsers();

}
