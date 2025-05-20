package com.wahoweb.rental.car.dto;

import lombok.Data;

@Data
public class WishlistDTO {
    private int wishlistId;
    private int userId;
    private int carId;
}
