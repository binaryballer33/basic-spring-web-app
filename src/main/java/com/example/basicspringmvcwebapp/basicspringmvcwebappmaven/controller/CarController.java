package com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.controller;


import com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.model.Car;
import com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // GET all cars
    @GetMapping
    public List<Car> getAllCars() {
        System.out.println("Getting all cars");
        return carService.findAll();
    }

    // GET a single car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.findById(id);
        System.out.println("Getting car by ID: " + id + " -> " + car);
        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new car
    @PostMapping("/post")
    public RedirectView createCar(@ModelAttribute Car car) {
        System.out.println("car to create: " + car);
        carService.save(car);
        return new RedirectView("/");
    }

    // PUT (update) a car
    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        System.out.println("updated car: " + updatedCar);
        Optional<Car> existingCar = carService.findById(id);
        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setMake(updatedCar.getMake());
            car.setModel(updatedCar.getModel());
            car.setMpg(updatedCar.getMpg());
            car.setEngineType(updatedCar.getEngineType());
            System.out.println("Car updated successfully");
            return ResponseEntity.ok(carService.save(car));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a car
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        System.out.println("Deleting car with ID: " + id);
        if (carService.existsById(id)) {
            carService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
