package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.CarRepository;
import com.spring.dto.CarDTO;


@Transactional(readOnly = true)
@Service
public class CarService {

    private final CarRepository carRepository ;

    public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public List<CarDTO> findAll() {
        return carRepository.findAll();
    }

	@Transactional
    public void save(CarDTO car) {
    	carRepository.save(car);
    }

    public CarDTO findById(Integer id) {
        return carRepository.findById(id).orElseThrow(
        		() -> new IllegalArgumentException("해당 자동차를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public void edit(CarDTO car) {
        CarDTO raw = findById(car.getCarId());
        raw.setBrand(car.getBrand());
        raw.setMileage(car.getMileage());
        raw.setModel(car.getModel());
        raw.setPrice(car.getPrice());
        raw.setYear(car.getYear());
    }
}




