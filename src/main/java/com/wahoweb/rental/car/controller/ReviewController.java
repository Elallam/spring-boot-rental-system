package com.wahoweb.rental.car.controller;

import com.wahoweb.rental.car.dto.ReviewDTO;
import com.wahoweb.rental.car.entity.Review;
import com.wahoweb.rental.car.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @GetMapping("/{carId}")
    public List<Review> getReviews(@PathVariable int carId){
        return reviewService.getReviewByCarId(carId);
    }

    @GetMapping()
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @PostMapping("/add")
    public ResponseEntity<List<Review>> addReview(@RequestBody  ReviewDTO reviewDTO){
        List<Review> reviews = reviewService.addReview(reviewDTO);
        return  ResponseEntity.ok(reviews);
    }

    @PutMapping("/edit")
    public ResponseEntity updateReview(@RequestBody ReviewDTO reviewDTO){
        reviewService.updateReview(reviewDTO);
        return ResponseEntity.ok("Review has been updated");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
