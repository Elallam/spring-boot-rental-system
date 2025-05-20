package com.wahoweb.rental.car.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String message);
}
