package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dto.BookingDTO;
import com.wahoweb.rental.car.entity.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    public List<Booking> getBookings();
    public void confirmBooking(BookingDTO bookingDto);

    public List<Booking> getCustomerBooking(int customerId);
    public Booking getCarBooking(int carId);

    public Booking getBooking(BookingDTO bookingDto);

    public ResponseEntity deleteBooking(int bookingId);
}
