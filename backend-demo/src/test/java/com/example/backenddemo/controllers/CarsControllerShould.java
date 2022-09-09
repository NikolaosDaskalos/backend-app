package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(CarsController.class)
public final class CarsControllerShould {

    @MockBean
    private CarRepository carRepository;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    Car car1 = new Car(1L, "Kia", "Seed", 2020, "Red");
    Car car2 = new Car(2L, "Toyota", "Prius", 2018, "Blue");
    Car car3 = new Car(3L, "Audi", "A4", 2015, "Silver");

    @Test
    @DisplayName("Get return list of cars successfully")
    public void CarsListTest() throws Exception {
        List<Car> cars = new ArrayList<> (Arrays.asList(car1,car2,car3));

        when(carRepository.findAll()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[2].manufacture",is("Audi")))
                .andExpect(jsonPath("$[1].model",is("Prius")))
                .andExpect(jsonPath("$[0].color",is("Red")));

    }
    @Disabled
    @Test
    public void getCarByIdTest() throws Exception {
//        when(carRepository.findById(car1.getCar_id())).thenReturn(Optional.of(car1));
        when(carRepository.getReferenceById(car1.getCar_id())).thenReturn(car1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
//                .andExpect(jsonPath("$[0].manufacture",is("Kia")))
//                    .andExpect(jsonPath("$[0].model",is("Seed")))
//                    .andExpect(jsonPath("$[0].model_year",is("2020")))
//                    .andExpect(jsonPath("$[0].color",is("Red")));

    }





}

