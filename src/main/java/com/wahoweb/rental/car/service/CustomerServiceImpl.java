package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dao.CustomerRepo;
import com.wahoweb.rental.car.dto.CustomerDTO;
import com.wahoweb.rental.car.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepo customerRepository;

    @Override
    public Customer getCustomer(int customerId) {

        return customerRepository.findByCustomerId(customerId);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDto) {
        customerRepository.save(getCustomer(customerDto.getCustomerId()));
    }

    @Override
    public Customer getCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();

        customer.setCustomerId(customerDto.getCustomerId());
        return customer;
    }
}
