package com.wahoweb.rental.car.controller;

import com.wahoweb.rental.car.dto.CustomerDTO;
import com.wahoweb.rental.car.entity.Customer;
import com.wahoweb.rental.car.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        return customerService.getCustomer(customerId);
    }

    @PutMapping
    public void updateCustomer(@RequestBody CustomerDTO customerDto) {
        customerService.updateCustomer(customerDto);
    }
}
