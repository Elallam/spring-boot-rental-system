package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
