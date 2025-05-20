package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dao.CarRepo;
import com.wahoweb.rental.car.dao.ReviewRepo;
import com.wahoweb.rental.car.dao.UserRepo;
import com.wahoweb.rental.car.dto.ReviewDTO;
import com.wahoweb.rental.car.entity.Car;
import com.wahoweb.rental.car.entity.Review;
import com.wahoweb.rental.car.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private UserRepo userRepo;

    private Review tdo2entity(ReviewDTO reviewTDO){
        Review review = new Review();
        review.setReviewId(reviewTDO.getReviewId());
        review.setComment(reviewTDO.getComment());
        review.setRating(reviewTDO.getRating());
        review.setDatePosted(reviewTDO.getDatePosted());
        Car car = carRepo.findById(reviewTDO.getCarId());
        review.setCar(car);
        Optional<User> user = userRepo.findById(reviewTDO.getUserId());
        review.setUser(user.get());
        return review;
    }
    @Override
    public List<Review> addReview(ReviewDTO reviewTDO) {
        Review review = tdo2entity(reviewTDO);
        reviewRepo.save(review);
        int n = (reviewRepo.findReviewsByCarCarId(review.getCar().getCarId()).isEmpty()) ? 1 : reviewRepo.findReviewsByCarCarId(review.getCar().getCarId()).size();
        double rating = (reviewTDO.getRating() + Double.parseDouble(review.getCar().getRating()))/(n+1);
        review.getCar().setRating("" +  new DecimalFormat("#.#").format(rating));
        carRepo.save(review.getCar());
        List<Review> reviews = reviewRepo.findReviewsByCarCarId(reviewTDO.getCarId());
        return reviews;
    }

    @Override
    public void updateReview(ReviewDTO reviewTDO) {
        Review review = tdo2entity(reviewTDO);
        reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewByCarId(int carId) {
        return reviewRepo.findReviewsByCarCarId(carId);
    }

    @Override
    public void deleteReview(int reviewId) {
        // Step 1: Fetch the review only once
        Optional<Review> reviewOptional = reviewRepo.findById(reviewId);

        if (!reviewOptional.isPresent()) {
            // Or throw a custom exception e.g., ReviewNotFoundException
            System.err.println("Review with ID " + reviewId + " not found.");
            return;
        }

        Review reviewToDelete = reviewOptional.get();
        Car car = reviewToDelete.getCar();

        // Step 2: Check if the associated car exists
        if (car == null) {
            // This case might indicate data integrity issues or a review not linked to a car
            System.err.println("Review with ID " + reviewId + " is not associated with any car or car is null.");
            // Proceed to delete the review itself as it's not linked properly
            reviewRepo.delete(reviewToDelete); // or reviewRepo.deleteById(reviewId);
            return;
        }

        // Step 3: Adjust the car's rating
        String currentCarRatingStr = car.getRating();
        double ratingOfReviewBeingDeleted = reviewToDelete.getRating(); // Assuming Review.getRating() returns double

        if (currentCarRatingStr != null && !currentCarRatingStr.trim().isEmpty()) {
            try {
                double currentCarRatingNum = Double.parseDouble(currentCarRatingStr);

                // Perform the subtraction as requested: new_rating = current_rating - review_rating
                double newCarRatingNum = currentCarRatingNum - ratingOfReviewBeingDeleted;

                // Set the updated rating back to the car object
                // You might want to format this string, e.g., to a certain number of decimal places
                car.setRating(String.valueOf(newCarRatingNum));

                // Save the updated car information
                carRepo.save(car);

            } catch (NumberFormatException e) {
                // Handle cases where the car's current rating string is not a valid double
                System.err.println("Error parsing car's current rating: " + currentCarRatingStr +
                        " for car ID " + car.getCarId() + ". Car rating not updated. " + e.getMessage());
                // Decide if you still want to delete the review in this case
            }
        } else {
            // Handle cases where the car's current rating is null or empty
            // This might mean it's the first review, or rating calculation is handled differently.
            // For now, we're just logging. The car's rating won't be updated.
            System.err.println("Car ID " + car.getCarId() + " has a null or empty rating string. Car rating not updated.");
        }

        // Step 4: Delete the review
        reviewRepo.delete(reviewToDelete); // or reviewRepo.deleteById(reviewId);
        System.out.println("Review with ID " + reviewId + " deleted successfully.");
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }
}
