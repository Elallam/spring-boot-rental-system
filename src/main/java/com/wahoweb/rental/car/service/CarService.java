package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dto.CarDTO;
import com.wahoweb.rental.car.entity.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {

    public Car addCar(CarDTO cardto) throws IOException;
    public Car getCar(CarDTO cardto) throws IOException;
    public Car getCar(String licenceNumber);
    public List<Car> getAllCars();
    public boolean updateCar(CarDTO carDto) throws IOException;
    public boolean deleteCar(int carId);

    public Car getCar(int carId);

    public List<Car> searchBy(String name, String model, double minPrice, double maxPrice);
    public List<Car> searchByName(String name, double minPrice, double maxPrice);

    public List<Car> searchByModel(String model, double minPrice, double maxPrice);

    public List<Car> searchByPrice(double minPrice, double maxPrice);

    List<Car> searByKeyWord(String keyword);
}
