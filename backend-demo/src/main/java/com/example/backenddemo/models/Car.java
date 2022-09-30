package com.example.backenddemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name= "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
    @Id
    @Column(name= "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String manufacture;
    private String model;
    @Column(name= "model_year")
    private Integer modelYear;
    private String color;

    //Default Constructor
    public Car (){}

    public Car(Long carId, String manufacture, String model,
               Integer modelYear, String color){
        this.carId = carId;
        this.manufacture = manufacture;
        this.model = model;
        this.modelYear = modelYear;
        this.color = color;
    }

    //region Getters & Setters
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
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
    public Integer getModelYear() {
        return modelYear;
    }
    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    //endregion
}
