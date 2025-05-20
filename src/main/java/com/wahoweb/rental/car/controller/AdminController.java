package com.wahoweb.rental.car.controller;

import com.wahoweb.rental.car.dto.CarDTO;
import com.wahoweb.rental.car.service.CarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private CarService carService;

    @PostMapping("/add-car")
    public ResponseEntity<?> postCar(@RequestBody CarDTO carDTO) throws IOException {
        this.carService.addCar(carDTO);
        return ResponseEntity.ok().build();
    }
}
