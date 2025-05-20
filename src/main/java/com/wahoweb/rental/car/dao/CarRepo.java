package com.wahoweb.rental.car.dao;

import com.wahoweb.rental.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.naming.InsufficientResourcesException;
import java.util.List;


@CrossOrigin("*")
public interface CarRepo extends JpaRepository<Car, Integer> {
    Car findByLicenceNumber(String licenceNumber);

    Car findById(int carId);

    @Query( value = "SELECT c FROM Car c WHERE c.name like %?1% and c.model like %?2% and c.rentPrice >= ?3 and c.rentPrice <= ?4")
    List<Car> findBy(String name, String model, double minPrice, double maxPrice);

    @Query( value = "SELECT c FROM Car c WHERE c.name like %?1%  and c.rentPrice >= ?2 and c.rentPrice <= ?3")
    List<Car> findByName(String name, double minPrice, double maxPrice);

    @Query( value = "SELECT c FROM Car c WHERE  c.model like %?1% and c.rentPrice >= ?2 and c.rentPrice <= ?3")
    List<Car> findByModel(String model, double minPrice, double maxPrice);

    @Query( value = "SELECT c FROM Car c WHERE c.rentPrice >= ?1 and c.rentPrice <= ?2")
    List<Car> findByPrice(double minPrice, double maxPrice);

    @Query( value = "SELECT c FROM Car c WHERE c.model like %?1% or c.name  like %?1%")
    List<Car> findByKeyword(String keyword);

    void deleteByLicenceNumber(String licenceNumber);
}
