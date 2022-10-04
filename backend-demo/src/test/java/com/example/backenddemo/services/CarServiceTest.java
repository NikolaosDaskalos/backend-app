package com.example.backenddemo.services;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.repositories.CarRepository;
import com.example.backenddemo.services.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("CarService tests")
class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    Car mockCar = new Car(1L,"Toyota","Corolla", 2019,"Black");

    @Test
    @DisplayName("getAllCars Successfully")
    void getAllCarsTest() {
        Car mockCar2 =new Car(2L, "Ford", "Puma", 2021, "Silver");
        when(carRepository.findAll()).thenReturn(Arrays.asList(mockCar,mockCar2));
        assertNotNull(carService.getAllCars());
        assertIterableEquals(carRepository.findAll(), carService.getAllCars());
    }

    @Test
    @DisplayName("find car by Id Successfully")
    void getCarById() {
        when(carRepository.getReferenceById(1l)).thenReturn(mockCar);
        Car returnedCar = carService.getCarById(1l);
        assertNotNull(returnedCar);
        assertSame(returnedCar, mockCar,()->"Car should be the same");
        assertEquals("Toyota", returnedCar.getManufacture());
    }
    @Test
    @DisplayName("find car by Id Not found")
    void getCarByIdNotFound() {
        when(carRepository.getReferenceById(1l)).thenReturn(null);
        Car returnedCar = carService.getCarById(1l);
        assertNull(returnedCar,()->"Car should be null");
    }

    @Test
    @DisplayName("saveCar Successfully")
    void saveCar() {
        when(carRepository.saveAndFlush(mockCar)).thenReturn(mockCar);
        Car returnedCar = carService.saveCar(mockCar);
        assertNotNull(returnedCar);
        assertSame(returnedCar, mockCar);
    }

    @Test
    @DisplayName("deleteCarById Successfully")
    void deleteCarById() {
        carService.deleteCarById(1L);
        verify(carRepository,times(1)).deleteById(1l);
    }

    @Test
    @DisplayName("updateCar Successfully")
    void updateCar() {
        Car carUpdate = new Car(3L,"Peugeot","2008", 2021,"Red");
        when(carRepository.getReferenceById(1L)).thenReturn(mockCar);
        carService.updateCar(1L,carUpdate);
        verify(carRepository).saveAndFlush(mockCar);
    }
}