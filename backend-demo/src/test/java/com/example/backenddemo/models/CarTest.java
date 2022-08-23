package com.example.backenddemo.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Nested
    @DisplayName("Create")
    class CarCreate{
        @Test
        @DisplayName("empty car instance successfully")
        void createEmptyCar(){
            Car emptyCar = new Car();
            assertAll(
                    ()-> assertEquals(null,emptyCar.getCar_id()),
                    ()-> assertEquals(null,emptyCar.getManufacture()),
                    ()-> assertEquals(null,emptyCar.getModel()),
                    ()-> assertEquals(null,emptyCar.getModel_year()),
                    ()-> assertEquals(null,emptyCar.getColor())
            );
        }

        @Test
        @DisplayName("non empty car instance successfully")
        void createNonEmptyCar(){
            Car car = new Car(1L,"Toyota","Corolla", 2019,"Black");
            assertAll(
                    ()-> assertEquals(1L,car.getCar_id()),
                    ()-> assertEquals("Toyota",car.getManufacture()),
                    ()-> assertEquals("Corolla",car.getModel()),
                    ()-> assertEquals(2019,car.getModel_year()),
                    ()-> assertEquals("Black",car.getColor())
            );
        }
    }
}