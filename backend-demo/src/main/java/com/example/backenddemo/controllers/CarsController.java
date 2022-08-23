package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> carList(){return carRepository.findAll();}

    @GetMapping
    @RequestMapping("{id}")
    public Car getCar(@PathVariable Long id){
        return carRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar (@RequestBody final Car car){
        return carRepository.saveAndFlush(car);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable Long id){
        carRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        Car existingCar = carRepository.getReferenceById(id);
        BeanUtils.copyProperties(car, existingCar, "car_id");
        return carRepository.saveAndFlush(existingCar);
    }
}
