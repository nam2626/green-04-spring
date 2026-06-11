package com.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dto.CarDTO;

public interface CarRepository extends JpaRepository<CarDTO, Integer>{

}
