package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dao.BookingRepo;
import com.wahoweb.rental.car.dao.CarRepo;
import com.wahoweb.rental.car.dto.CarDTO;
import com.wahoweb.rental.car.entity.Booking;
import com.wahoweb.rental.car.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepo carRepository;

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public Car addCar(CarDTO cardto) throws IOException {
        return carRepository.save(getCar(cardto));
    }

    @Override
    public Car getCar(CarDTO cardto) throws IOException {
        Car car = new Car();
        car.setCarId(cardto.getCarId());
        car.setAvailable(true);
        car.setMileage(cardto.getMileage());
        car.setName(cardto.getName());
        car.setModel(cardto.getModel());
        car.setRating("0.0");
        car.setFuelType(cardto.getFuelType());
        if(cardto.getImage() != null) car.setImg(cardto.getImage().getBytes());
        car.setRentPrice(cardto.getRentPrice());
        car.setTransmission(cardto.getTransmission());
        car.setNumberOfPassanger(cardto.getNumberOfPassanger());
        car.setDescription(cardto.getDescription());
        car.setVideoUrl(cardto.getVideoUrl());
        car.setNumberOfDoors(cardto.getNumberOfDoors());
        car.setNumberOfSuitcases(cardto.getNumberOfSuitcases());
        return car;
    }

    @Override
    public Car getCar(String licenceNumber) {
        return carRepository.findByLicenceNumber(licenceNumber);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public boolean updateCar(CarDTO carDto) throws IOException {
        Car car = carRepository.findById(carDto.getCarId());
        if(car != null){
            car.setAvailable(carDto.isAvailable());
            car.setMileage(carDto.getMileage());
            car.setName(carDto.getName());
            car.setModel(carDto.getModel());
            car.setFuelType(carDto.getFuelType());
            if(carDto.getImage() != null) car.setImg(carDto.getImage().getBytes());
            car.setRentPrice(carDto.getRentPrice());
            car.setTransmission(carDto.getTransmission());
            car.setNumberOfPassanger(carDto.getNumberOfPassanger());
            car.setDescription(carDto.getDescription());
            car.setVideoUrl(carDto.getVideoUrl());
            car.setNumberOfDoors(carDto.getNumberOfDoors());
            car.setNumberOfSuitcases(carDto.getNumberOfSuitcases());
            carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCar(int carId) {
        Car car = carRepository.findById(carId);
        if(car != null){
            List<Booking> bookings = bookingRepo.findAllByCar(car);
            bookingRepo.deleteAll(bookings);

            carRepository.delete(car);
            return true;
        }
        return false;
    }

    @Override
    public Car getCar(int carId){
        return carRepository.findById(carId);
    }

    @Override
    public List<Car> searchBy(String name, String model, double minPrice, double maxPrice) {
        System.out.println(name + " " + model+ " " + minPrice +  " " + maxPrice);
        return carRepository.findBy(name, model, minPrice, maxPrice);
    }

    @Override
    public List<Car> searchByName(String name, double minPrice, double maxPrice) {
        return carRepository.findByName(name, minPrice, maxPrice);
    }

    @Override
    public List<Car> searchByModel(String model, double minPrice, double maxPrice) {
        return carRepository.findByModel(model, minPrice, maxPrice);
    }

    @Override
    public List<Car> searchByPrice(double minPrice, double maxPrice) {
        return carRepository.findByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Car> searByKeyWord(String keyword) {
        return carRepository.findByKeyword(keyword);
    }

}
