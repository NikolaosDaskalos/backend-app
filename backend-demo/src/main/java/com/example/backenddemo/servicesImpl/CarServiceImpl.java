package com.example.backenddemo.servicesImpl;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.repositories.CarRepository;
import com.example.backenddemo.services.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carService){
        this.carRepository = carService;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.getReferenceById(id);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.saveAndFlush(car);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);

    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepository.getReferenceById(id);
        BeanUtils.copyProperties(car, existingCar, "car_id");
        return carRepository.saveAndFlush(existingCar);
    }
}
