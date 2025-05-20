package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dto.WishlistDTO;
import com.wahoweb.rental.car.entity.Wishlist;

public interface WishlistService {

    public void addToWishlist(WishlistDTO wishlistDTO);
    public void removeFromWishlist(WishlistDTO wishlistDTO);
}
