package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.Booking;
import com.wahoweb.rental.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByCustomerCustomerId(int customerId);
    List<Booking> findAllByCar(Car car);
    Booking findByCarCarId(int carId);

    Booking findByBookingId(int bookingId);

}
