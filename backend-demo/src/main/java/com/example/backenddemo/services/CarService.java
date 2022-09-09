package com.example.backenddemo.services;

import com.example.backenddemo.models.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();
    Car getCarById(Long id);
    Car saveCar(Car car);
    void deleteCarById(Long id);
    Car updateCar(Long id,Car car);

}
