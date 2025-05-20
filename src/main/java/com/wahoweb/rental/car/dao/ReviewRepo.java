package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findReviewsByCarCarId(int carId);
    
}
