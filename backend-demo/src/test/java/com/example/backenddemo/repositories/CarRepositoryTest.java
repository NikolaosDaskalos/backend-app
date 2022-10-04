package com.example.backenddemo.repositories;

import com.example.backenddemo.models.Car;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")

class CarRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CarRepository carRepository;

    public ConnectionHolder getConnectionHolder()  {
        return ()-> dataSource.getConnection();
    }

    @Test
    @DataSet("cars.yml")
    @DisplayName("Find all cars Successfully")
    void findAllCarsTest(){
        List<Car> cars= carRepository.findAll();
        assertAll(
                ()-> assertNotNull(cars),
                ()-> assertEquals(2,cars.size()),
                ()->assertEquals("Toyota",carRepository.findAll().get(0).getManufacture())
        );
    }

    @Test
    @DataSet("cars.yml")
    @DisplayName("Find car by id Successfully")
    void findCarByIdTest(){
        Optional<Car> car1 = carRepository.findById(1L);
        Optional<Car> car2 = carRepository.findById(2L);
        assertAll(
                ()-> assertNotNull(car1),
                ()-> assertNotNull(car2),
                ()->assertEquals("Toyota",car1.get().getManufacture()),
                ()->assertEquals(2021,car2.get().getModelYear())
                );
    }

    @Test
    @DataSet("cars.yml")
    @DisplayName("Save & flush car Successfully")
    @Disabled
    void saveAndFlushCarTest(){
        Car car3 = new Car(3L,"Ford","Puma",2020,"Black");
        Car savedCar = carRepository.saveAndFlush(car3);
        assertAll(
                ()-> assertNotNull(savedCar),
                ()->assertEquals("Ford",savedCar.getManufacture()),
                ()->assertEquals("Puma",savedCar.getModel()),
                ()->assertEquals(2020,savedCar.getModelYear()),
                ()->assertEquals("Black",savedCar.getColor())
        );
    }
}