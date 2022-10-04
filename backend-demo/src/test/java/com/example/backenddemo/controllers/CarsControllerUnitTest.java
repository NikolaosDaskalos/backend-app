package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.services.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Cars Controller Unit Tests")
class CarsControllerUnitTest {

    private CarService carService = mock(CarService.class);
    private CarsController carsController = new CarsController(carService);
    private Car mockCar = new Car(1L,"Toyota","Corolla", 2019,"Black");

    @Test
    @DisplayName("Get all Cars Successfully")
    void getCarListTest() {
        Car mockCar2 = new Car(2L,"Peugeot","2008", 2021,"Red");
        when(carService.getAllCars()).thenReturn(Arrays.asList(mockCar,mockCar2));
        assertAll(
                ()->assertNotNull(carsController.getCarList()),
        ()-> assertSame(carService.getAllCars(),carsController.getCarList())
        );
    }

    @Test
    @DisplayName("Get Car by Id Successfully")
    void getCarByIDTest() {
        when(carService.getCarById(1L)).thenReturn(mockCar);
        assertAll(
                ()->assertNotNull(carsController.getCar(1L)),
                ()-> assertSame(carService.getCarById(1L),carsController.getCar(1L))
        );
    }

    @Test
    @DisplayName("Create Car Successfully")
    void createCarTest() {
        when(carService.saveCar(mockCar)).thenReturn(mockCar);
        assertAll(
                ()->assertNotNull(carsController.createCar(mockCar)),
        ()-> assertSame(carService.saveCar(mockCar),carsController.createCar(mockCar))
        );
    }

    @Test
    @DisplayName("Delete Car Successfully")
    void deleteCar() {
        carsController.deleteCar(1L);
        carsController.deleteCar(1L);
        verify(carService,times(2)).deleteCarById(1L);
    }

    @Test
    @DisplayName("Update Car Successfully")
    void update() {
        Car carUpdate = new Car(3L,"Peugeot","2008", 2021,"Red");
        when(carService.updateCar(1L,carUpdate)).thenReturn(mockCar);
        assertAll(
                ()-> assertNotNull(carsController.update(1L,carUpdate)),
                ()-> assertSame(carService.updateCar(1L,carUpdate),carsController.update(1L,carUpdate))
        );
    }
}