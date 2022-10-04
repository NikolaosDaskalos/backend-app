package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Cars Controller Integration Tests")
public class CarsControllerIntegrationTest {
    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    private Car car = new Car(1L, "Kia", "Seed", 2020, "Red");

    @Test
    @DisplayName("Get all cars successfully")
    public void CarsListTest() throws Exception {
        Car car2 = new Car(2L, "Ford", "Puma", 2021, "Silver");
        List<Car> cars = Arrays.asList(car,car2);
        when(carService.getAllCars()).thenReturn(cars);
        mockMvc.perform(get("/api/cars/"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Get car by Id successfully")
    public void getCarByIdTest() throws Exception {
        when(carService.getCarById(1L)).thenReturn(car);
        mockMvc.perform(get("/api/cars/{id}", 1L))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId").value(1L))
                .andExpect(jsonPath("$.manufacture").value("Kia"))
                .andExpect(jsonPath("$.model").value("Seed"))
                .andExpect(jsonPath("$.modelYear").value(2020))
                .andExpect(jsonPath("$.color").isNotEmpty());
    }

    @Test
    @DisplayName("Post new car successfully")
    void postNewCarTest() throws Exception {
        when((carService.saveCar(car))).thenReturn(car);

        mockMvc.perform(post("/api/cars/",car)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(car)))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Delete Car successfully")
    void deleteCarTest() throws Exception {
        mockMvc.perform(delete("/api/cars/{id}",1L))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Put update car successfully")
    void putRequestUpdateCarTest() throws Exception {
        Car update = new Car(9L, "Ford", "Puma", 2021, "Silver");
        when(carService.updateCar(1L,update)).thenReturn(update);

        mockMvc.perform(put("/api/cars/{id}",car.getCarId(),update)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(update)))
                        .andExpect(status().isOk());
    }

    static String asJsonString(final Object o) {
        try{
            return new ObjectMapper().writeValueAsString(o);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

