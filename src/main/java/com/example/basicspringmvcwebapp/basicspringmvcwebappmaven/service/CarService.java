package com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.service;


import com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarService extends JpaRepository<Car, Long> {
}
