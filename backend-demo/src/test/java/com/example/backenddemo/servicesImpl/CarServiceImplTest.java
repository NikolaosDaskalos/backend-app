package com.example.backenddemo.servicesImpl;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.repositories.CarRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("CarService implementation class tests")
@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarServiceImpl carServiceImpl;
    Car car = new Car(1L,"Toyota","Corolla", 2019,"Black");

    @Test
    @DisplayName("getAllCars Successfully")
    void getAllCarsTest() {
        //when
        carServiceImpl.getAllCars();
        //then
        verify(carRepository).findAll();

    }

    @Test
    @DisplayName("getCarById Successfully")
    void getCarById() {
        Long id = 10L;
        when(carServiceImpl.getCarById(1l)).thenReturn(car);
        assertEquals("Toyota",carServiceImpl.getCarById(1L).getManufacture());
        assertEquals(car,carServiceImpl.getCarById(1L));
        carServiceImpl.getCarById(id);
        verify(carRepository).getReferenceById(id);
    }

    @Test
    @DisplayName("saveCar Successfully")
    void saveCar() {
        Car car = new Car();
        carServiceImpl.saveCar(car);
        verify(carRepository).saveAndFlush(car);
    }

    @Test
    @DisplayName("deleteCarById Successfully")
    void deleteCarById() {
        Long id = 10L;
        carServiceImpl.deleteCarById(id);
        verify(carRepository).deleteById(id);

    }

    @Test
    @DisplayName("updateCar Successfully")
    void updateCar() {
        Car carUpdate = new Car(3L,"Peugeot","2008", 2021,"Red");
        Car carReturned = new Car(car.getCarId(), "Peugeot","2008", 2021,"Red");
        when(carServiceImpl.getCarById(car.getCarId())).thenReturn(car);
       when(carServiceImpl.updateCar(1L,carUpdate)).thenReturn(carReturned);
       assertEquals(carReturned,carServiceImpl.updateCar(1L,carUpdate));
    }
}