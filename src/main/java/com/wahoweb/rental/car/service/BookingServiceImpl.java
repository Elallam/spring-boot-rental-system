package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dao.BookingRepo;
import com.wahoweb.rental.car.dao.CarRepo;
import com.wahoweb.rental.car.dao.CustomerRepo;
import com.wahoweb.rental.car.dto.BookingDTO;
import com.wahoweb.rental.car.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepository;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }
    @Override
    public void confirmBooking(BookingDTO bookingDto) {
        System.out.println(bookingDto.toString());
        Booking booking =getBooking(bookingDto);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getCustomerBooking(int customerId) {
        return bookingRepository.findAllByCustomerCustomerId(customerId);
    }

    public Booking getCarBooking(int carId){
        return bookingRepository.findByCarCarId(carId);
    }

    @Override
    public Booking getBooking(BookingDTO bookingDto) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDto.getBookingId());
        booking.setBookStartDate(bookingDto.getBookStartDate());
        booking.setBookEndDate(bookingDto.getBookEndDate());
        booking.setCustomer(customerRepo.findByCustomerId(bookingDto.getCustomerId()));
        booking.setCar(carRepo.findById(bookingDto.getCarId()));
        booking.setTotal(bookingDto.getTotal());
        booking.setCustomerEmail(bookingDto.getCustomerEmail());
        booking.setEndLocation(bookingDto.getEndLocation());
        booking.setPickLocation(bookingDto.getPickLocation());
        booking.setUserName(bookingDto.getUserName());
        booking.setUserPhone(bookingDto.getUserPhone());
        return booking;
    }

    @Override
    public ResponseEntity deleteBooking(int bookingId){
        Booking booking = bookingRepository.findByBookingId(bookingId);
        if(booking != null){
            bookingRepository.delete(booking);
            System.out.println("Booking deleted : " + bookingId);
            return ResponseEntity.ok("Booking has been deleted");
        }
        return  ResponseEntity.ok("Booking not found");
    }
}
