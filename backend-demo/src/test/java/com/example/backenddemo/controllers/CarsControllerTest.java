package com.example.backenddemo.controllers;

import com.example.backenddemo.models.Car;
import com.example.backenddemo.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(MockitoExtension.class)
public final class CarsControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarsController carsController;

    Car car1 = new Car(1L, "Kia", "Seed", 2020, "Red");
    Car car2 = new Car(2L, "Toyota", "Prius", 2018, "Blue");
    Car car3 = new Car(3L, "Audi", "A4", 2015, "Silver");

    @BeforeEach
    public void init(){
    this.mockMvc = MockMvcBuilders.standaloneSetup(carsController).build();
    }

        @Test
        public void getAllCarsTest() throws Exception {
            List<Car> cars = new ArrayList<>(Arrays.asList(car1,car2,car3));
            Mockito.when(carRepository.findAll()).thenReturn(cars);
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/cars")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                    .andExpect(jsonPath("$[2].manufacture",is("Audi")))
                    .andExpect(jsonPath("$[1].model",is("Prius")))
                    .andExpect(jsonPath("$[0].color",is("Red")));

        }

        @Test
        @Disabled
        public void getCarByIdTest() throws Exception {
            Mockito.when(carRepository.findById(car1.getCar_id())).thenReturn(Optional.of(car1));

            mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/cars/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                    .andExpect(jsonPath("$[0].manufacture",is("Kia")));
//                    .andExpect(jsonPath("$[0].model",is("Seed")))
//                    .andExpect(jsonPath("$[0].model_year",is("2020")))
//                    .andExpect(jsonPath("$[0].color",is("Red")));

        }

    @Test
    @Disabled
    public void createCarWithPostTest() throws Exception{
        Car newCar = Car.builder()
                .car_id(4L)
                .manufacture("Ford")
                .model("Puma")
                .model_year(2021)
                .color("Gray")
                .build();

        Mockito.when(carRepository.save(newCar)).thenReturn(newCar);

        String content = objectWriter.writeValueAsString(newCar);

       MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders.post("/api/cars")
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .content(content);

       mockMvc.perform(mockRequest)
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$",notNullValue()))
               .andExpect(jsonPath("$.manufacture",is("Ford")));
    }
}

