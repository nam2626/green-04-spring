package com.spring.controller;

import com.spring.dto.CarDTO;
import com.spring.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/cars")
@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ModelAndView list(ModelAndView view) {
        view.addObject("cars", carService.findAll());
        view.setViewName("list");
        return view;
    }

    @GetMapping("/new")
    public ModelAndView newCar(ModelAndView view) {
        view.addObject("car", new CarDTO());
        view.setViewName("form");
        return view;
    }
}








