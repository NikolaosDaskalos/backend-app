package com.example.backenddemo;

import com.example.backenddemo.models.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles ("test")
@AutoConfigureMockMvc
@DisplayName("Integration Tests")
public class CarIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet("cars.yml")
    @DisplayName("Get Car by id Successfully")
    void testGetProductById() throws Exception {
        mockMvc.perform(get("/api/cars/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.carId").value(1L))
                .andExpect(jsonPath("$.manufacture").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Prius"))
                .andExpect(jsonPath("$.modelYear").value(2019))
                .andExpect(jsonPath("$.color").value("Black"));
    }

    @Test
    @DataSet("cars.yml")
    @DisplayName("Get all cars successfully")
    public void testCarsList() throws Exception {
        mockMvc.perform(get("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].carId").value(1L))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DataSet(value = "cars.yml",strategy = SeedStrategy.IDENTITY_INSERT)
    @DisplayName("Post new car successfully")
    @Disabled
    void postNewCarTest() throws Exception {
        Car postCar = new Car(4L,"Toyota","Corolla", 2019,"Black");

        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postCar)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.carId").value(3L))
                .andExpect(jsonPath("$.manufacture").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"))
                .andExpect(jsonPath("$.modelYear").value(2019))
                .andExpect(jsonPath("$.color").value("Black"));
}
    @Test
    @DataSet("cars.yml")
    @DisplayName("Delete car successfully")
    void deleteCarTest() throws Exception {
        mockMvc.perform(delete("/api/cars/{id}",1L)
                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    @DataSet("cars.yml")
    @DisplayName("Put car update successfully")
    void putCarTest() throws Exception {
        Car putCar = new Car(3L,"Toyota","Corolla", 2019,"Black");

        mockMvc.perform(put("/api/cars/{id}",2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putCar)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.carId").value(2L))
                .andExpect(jsonPath("$.manufacture").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"))
                .andExpect(jsonPath("$.modelYear").value(2019))
                .andExpect(jsonPath("$.color").value("Black"));
    }

    static String asJsonString(final Object o) {
        try{
            return new ObjectMapper().writeValueAsString(o);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
