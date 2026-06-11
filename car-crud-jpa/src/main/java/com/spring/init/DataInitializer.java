package com.spring.init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.CarRepository;
import com.spring.dto.CarDTO;

import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {
	private final CarRepository carRepository;

	public DataInitializer(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		// 이미 데이터가 있다면 중복 생성을 방지하기 위한 조건문 (선택사항)
		if (carRepository.count() > 0) {
			return;
		}

		// 아이디(carId)와 날짜(registeredAt)는 null로 설정된 10개의 CarDTO 생성 및 저장
		carRepository.saveAll(List.of(new CarDTO(null, "Hyundai", "Avante", 2022, 15000, 2300, null),
				new CarDTO(null, "Hyundai", "Grandeur", 2023, 5000, 4200, null),
				new CarDTO(null, "Kia", "K5", 2021, 32000, 2100, null),
				new CarDTO(null, "Kia", "Sorento", 2023, 12000, 3800, null),
				new CarDTO(null, "Genesis", "G80", 2024, 2000, 6500, null),
				new CarDTO(null, "BMW", "5 Series", 2022, 25000, 5800, null),
				new CarDTO(null, "Mercedes-Benz", "E-Class", 2023, 8000, 7200, null),
				new CarDTO(null, "Audi", "A6", 2021, 41000, 4500, null),
				new CarDTO(null, "Tesla", "Model Y", 2023, 10000, 6200, null),
				new CarDTO(null, "Volvo", "XC60", 2022, 18000, 5400, null)));

		System.out.println("총 10개의 샘플 차량 데이터가 성공적으로 저장되었습니다.");
	}

}
