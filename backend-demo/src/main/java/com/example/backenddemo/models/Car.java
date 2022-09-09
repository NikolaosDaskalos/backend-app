package com.example.backenddemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name= "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_id;
    private String manufacture;
    private String model;
    private Integer model_year;
    private String color;

    //Default Constructor
    public Car (){}

    public Car(Long car_id, String manufacture, String model,
               Integer model_year, String color){
        this.car_id = car_id;
        this.manufacture = manufacture;
        this.model = model;
        this.model_year=model_year;
        this.color = color;
    }

    //region Getters & Setters
    public Long getCar_id() {
        return car_id;
    }
    public void setCar_id(Long car_id) {
        this.car_id = car_id;
    }
    public String getManufacture() {
        return manufacture;
    }
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Integer getModel_year() {
        return model_year;
    }
    public void setModel_year(Integer model_year) {
        this.model_year = model_year;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    //endregion
}
