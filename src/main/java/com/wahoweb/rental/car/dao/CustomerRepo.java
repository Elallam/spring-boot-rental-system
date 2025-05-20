package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
    Customer findByCustomerId(int customerId);
}
