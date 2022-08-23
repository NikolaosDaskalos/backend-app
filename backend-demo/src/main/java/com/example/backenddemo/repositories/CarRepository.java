package com.example.backenddemo.repositories;

import com.example.backenddemo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
