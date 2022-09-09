package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private CarService carService;

    public CarsController(CarService carService){
        super();
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCarList(){return carService.getAllCars();}

    @GetMapping("{id}")
    public Car getCar(@PathVariable Long id){
        return carService.getCarById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car){
        return carService.saveCar(car);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCarById(id);
    }

    @PutMapping("{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id,car);}
}
