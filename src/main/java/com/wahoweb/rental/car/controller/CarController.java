package com.wahoweb.rental.car.controller;

import com.wahoweb.rental.car.dto.CarDTO;
import com.wahoweb.rental.car.dto.WishlistDTO;
import com.wahoweb.rental.car.entity.Car;
import com.wahoweb.rental.car.service.CarService;
import com.wahoweb.rental.car.service.WishlistService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private WishlistService wishlistService;

    @PostMapping()
    public ResponseEntity<Car> addCar(CarDTO carDTO) throws IOException {
        // Create CarDTO or entity and pass to service layer
        Car car = carService.addCar(carDTO);
        CarDTO res = new CarDTO();
        return new ResponseEntity<>(car, HttpStatus.OK);
    }


    @GetMapping(value = "/registration/{carId}")
    public Car getCar(@PathVariable int carId) {
        return carService.getCar(carId);
    }


    @GetMapping
    public List<Car> getAllCar() {
        return carService.getAllCars();
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateCar(@ModelAttribute CarDTO carDto) throws IOException {
        // carDto will be populated from form fields.
        // carDto.getImage() will contain the new image if the client sends a file with the name "image".
        // If no file is sent with the name "image", carDto.getImage() will be null or empty.
        // The CarService's updateCar method should handle this:
        // - If a new image is present, update it.
        // - If no new image is present, keep the existing image.

        System.out.println("Update car method called for Car ID: " + carDto);
        if (carDto.getImage() != null && !carDto.getImage().isEmpty()) {
            System.out.println("New image received for update: " + carDto.getImage().getOriginalFilename() + " size: " + carDto.getImage().getSize());
        } else {
            System.out.println("No new image received for update. Existing image will be retained if not explicitly cleared in service.");
        }

        boolean success = carService.updateCar(carDto); // CarService needs to correctly handle carDto.getImage()
        if (success) {
            // Ideally, the service method might return the updated Car entity or DTO.
            // For now, returning a success message or the input DTO (which might not reflect all db changes like generated image URLs).
            // To return the updated car, carService.updateCar could return Car, then fetch it:
            // Car updatedCar = carService.getCar(carDto.getCarId());
            // return new ResponseEntity<>(updatedCar, HttpStatus.OK);
            // Or, if CarDTO should be returned:
            // return new ResponseEntity<>(carDto, HttpStatus.OK); // This returns the input DTO.
            return ResponseEntity.ok().body("Car with ID " + carDto.getCarId() + " updated successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with ID " + carDto.getCarId() + " not found or update failed.");
    }
    @DeleteMapping(value = "/{carId}")
    public void deleteCar(@PathVariable int carId) {
        carService.deleteCar(carId);
    }

    @GetMapping("/searchBy/{name}/{model}/{minPrice}/{maxPrice}")
    public List<Car> searchBy(@PathVariable String name,
                              @PathVariable String model,
                              @PathVariable double minPrice,
                              @PathVariable double maxPrice){
        return carService.searchBy(name, model, minPrice, maxPrice);
    }

    @GetMapping("/searchByName/{name}/{minPrice}/{maxPrice}")
    public List<Car> searchByName(@PathVariable String name,
                              @PathVariable double minPrice,
                              @PathVariable double maxPrice){
        return carService.searchByName(name, minPrice, maxPrice);
    }

    @GetMapping("/searchByModel/{model}/{minPrice}/{maxPrice}")
    public List<Car> searchByModel(
                              @PathVariable String model,
                              @PathVariable double minPrice,
                              @PathVariable double maxPrice){
        return carService.searchByModel(model, minPrice, maxPrice);
    }

    @GetMapping("/searchBy/{minPrice}/{maxPrice}")
    public List<Car> searchBy(
                              @PathVariable double minPrice,
                              @PathVariable double maxPrice){
        return carService.searchByPrice(minPrice, maxPrice);
    }

    @GetMapping("/searchByKeyWord/{keyword}")
    public List<Car> searchByKeyword(@PathVariable String keyword){
        return carService.searByKeyWord(keyword);
    }

    @PostMapping("/addToFav")
    public void addToWishList(@PathVariable WishlistDTO wishlistDTO){
        System.out.println("Add to wishList Called");
        wishlistService.addToWishlist(wishlistDTO);
    }

    @PostMapping("/deleteFav")
    public void removeFromWishList(@PathVariable WishlistDTO wishlistDTO){
        wishlistService.removeFromWishlist(wishlistDTO);
    }


}
