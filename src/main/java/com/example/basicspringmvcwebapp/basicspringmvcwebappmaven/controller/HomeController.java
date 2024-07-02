package com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.controller;

import com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.model.Car;
import com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CarService carService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "index.jsp";
    }
}