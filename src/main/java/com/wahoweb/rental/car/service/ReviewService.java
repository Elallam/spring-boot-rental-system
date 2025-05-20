package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dto.ReviewDTO;
import com.wahoweb.rental.car.entity.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> addReview(ReviewDTO reviewTDO);

    public void updateReview(ReviewDTO reviewTDO);

    public List<Review> getReviewByCarId(int carId);

    public void deleteReview(int reviewId);

    List<Review> getAllReviews();
}
