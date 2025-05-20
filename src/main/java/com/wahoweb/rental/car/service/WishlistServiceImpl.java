package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.dao.CarRepo;
import com.wahoweb.rental.car.dao.UserRepo;
import com.wahoweb.rental.car.dao.WishlistRepo;
import com.wahoweb.rental.car.dto.WishlistDTO;
import com.wahoweb.rental.car.entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    WishlistRepo wishlistRepo;
    @Autowired
    CarRepo carRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public void addToWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setCar(carRepo.findById(wishlistDTO.getCarId()));
        wishlist.setUser(userRepo.findById(wishlistDTO.getUserId()).get());
        wishlistRepo.save(wishlist);
    }

    @Override
    public void removeFromWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setCar(carRepo.findById(wishlistDTO.getCarId()));
        wishlist.setUser(userRepo.findById(wishlistDTO.getUserId()).get());
        wishlistRepo.delete(wishlist);
    }
}
