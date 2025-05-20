package com.wahoweb.rental.car.service;

import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Service
public class AdminServiceImpl  implements AdminService{

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
