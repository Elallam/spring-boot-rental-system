package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dto.UserDTO;
import com.wahoweb.rental.car.entity.User;

import java.util.Optional;

public interface UserService {
    public User checkLogin(UserDTO dto);
    public User registerUser(UserDTO userDTO);

    public boolean verifyUser(String token);
}
