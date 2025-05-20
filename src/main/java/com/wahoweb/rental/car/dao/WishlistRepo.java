package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
}
