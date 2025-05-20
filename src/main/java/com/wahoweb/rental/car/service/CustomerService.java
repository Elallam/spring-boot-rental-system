package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dto.CustomerDTO;
import com.wahoweb.rental.car.entity.Customer;

public interface CustomerService {
    public Customer getCustomer(int customerId);

    public void updateCustomer(CustomerDTO customerDto);

    public Customer getCustomer(CustomerDTO customerDto);
}
