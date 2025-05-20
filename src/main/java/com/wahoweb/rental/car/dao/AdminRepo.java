package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo  extends JpaRepository<Admin, Integer> {
}
