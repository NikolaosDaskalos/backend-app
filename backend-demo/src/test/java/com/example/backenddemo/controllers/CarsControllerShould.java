package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.models.User;
import com.example.backenddemo.servicesImpl.CarServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarsController.class)
@DisplayName("Cars Controller Tests")
public final class CarsControllerShould {

    @MockBean
    private CarServiceImpl carServiceImpl;
    @Autowired
    private MockMvc mockMvc;
        private Car car = new Car(1L, "Kia", "Seed", 2020, "Red");

    @Test
    @DisplayName("Get return list of cars successfully")
    public void CarsListTest() throws Exception {
        Car car2 = new Car(9L, "Ford", "Puma", 2021, "Silver");
        List<Car> cars = Arrays.asList(car,car2);
        when(carServiceImpl.getAllCars()).thenReturn(cars);
        mockMvc.perform(get("/api/cars/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Get car by Id successfully")
    public void getCarByIdTest() throws Exception {
        when(carServiceImpl.getCarById(car.getCarId())).thenReturn(car);

        mockMvc.perform(get("/api/cars/{id}", car.getCarId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId").value(1l))
                .andExpect(jsonPath("$.manufacture").value("Kia"))
                .andExpect(jsonPath("$.model").value("Seed"))
                .andExpect(jsonPath("$.modelYear").value("2020"))
                .andExpect(jsonPath("$.color").isNotEmpty());
    }

    @Test
    @DisplayName("Post new car successfully")
    void postNewCarTest() throws Exception {
        when((carServiceImpl.saveCar(car))).thenReturn(car);

        mockMvc.perform(post("/api/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"manufacture\": \"Kia\",\n" +
                                          "\"model\": \"Seed\",\n" +
                                          "\"modelYear\": 2020,\n" +
                                          "\"color\": \"Blue\"}"))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Delete Car successfully")
    void deleteCarTest() throws Exception {
        carServiceImpl.saveCar(car);
        mockMvc.perform(delete("/api/cars/{id}",car.getCarId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Put request update ucar successfully")
    void putRequestUpdateCarTest() throws Exception {
        Car update = new Car(9L, "Ford", "Puma", 2021, "Silver");
            when(carServiceImpl.updateCar(car.getCarId(),update)).thenReturn(update);

        mockMvc.perform(put("/api/cars/{id}",car.getCarId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"manufacture\": \"Ford\",\n" +
                                         "\"model\": \"Puma\",\n" +
                                         "\"modelYear\": 2021,\n" +
                                         "\"color\": \"Silver\"}"))
                        .andExpect(status().isOk());


    }
}

