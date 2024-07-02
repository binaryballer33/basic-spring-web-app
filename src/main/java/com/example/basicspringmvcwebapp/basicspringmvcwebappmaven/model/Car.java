package com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private double mpg;
    private String engineType;

    public Car() {
        this("Toyota", "Corolla", 40.0, "v4");
    }

    public Car(String make, String model) {
        this(make, model, 40.0, "v4");
    }

    public Car(String make, String model, String engineType) {
        this(make, model, 40.0, engineType);
    }

    public Car(String make, String model, double mpg) {
        this(make, model, mpg, "v4");

    }

    public Car(String make, String model, double mpg, String engineType) {
        this.make = make;
        this.model = model;
        this.mpg = mpg;
        this.engineType = engineType;
    }
}

