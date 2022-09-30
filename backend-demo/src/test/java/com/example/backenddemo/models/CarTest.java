package com.example.backenddemo.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Car class Tests")
class CarTest {

    @Nested
    @DisplayName("Create")
    class CarCreate{
        @Test
        @DisplayName("empty car instance successfully")
        void createEmptyCar(){

            Car emptyCar = new Car();
            assertAll(
                    ()-> assertEquals(null,emptyCar.getCarId()),
                    ()-> assertEquals(null,emptyCar.getManufacture()),
                    ()-> assertEquals(null,emptyCar.getModel()),
                    ()-> assertEquals(null,emptyCar.getModelYear()),
                    ()-> assertEquals(null,emptyCar.getColor())
            );
        }

        @Test
        @DisplayName("non empty car instance with parameters constructor successfully")
        void createNonEmptyCarWithParametersConstructor(){
            Car car = new Car(1L,"Toyota","Corolla", 2019,"Black");
            assertAll(
                    ()-> assertEquals(1L,car.getCarId()),
                    ()-> assertEquals("Toyota",car.getManufacture()),
                    ()-> assertEquals("Corolla",car.getModel()),
                    ()-> assertEquals(2019,car.getModelYear()),
                    ()-> assertEquals("Black",car.getColor())
            );
        }

        @Test
        @DisplayName("non empty car instance with default constructor successfully")
        void createNonEmptyCarWithDefaultConstructor(){
            Car car = new Car();
            car.setCarId(2L);
            car.setManufacture("Peugeot");
            car.setModel("2008");
            car.setModelYear(2020);
            car.setColor("Blue");
            assertAll(
                    ()-> assertEquals(2L,car.getCarId()),
                    ()-> assertEquals("Peugeot",car.getManufacture()),
                    ()-> assertEquals("2008",car.getModel()),
                    ()-> assertEquals(2020,car.getModelYear()),
                    ()-> assertEquals("Blue",car.getColor())
            );
        }
    }
}
