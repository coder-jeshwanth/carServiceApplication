package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.Car;
import com.practice.csa.requestDto.CarRequest;
import com.practice.csa.responseDto.CarResponse;

@Component
public class CarMapper {

	public Car mapToCar(CarRequest request) {
		Car car = new Car();
		
		car.setBrand(request.getBrand());
		car.setModel(request.getModel());
		
		return car;
	}
	
	public CarResponse mapToCarResponse(Car car) {
		
		CarResponse carResponse = new CarResponse();
		
		carResponse.setId(car.getCarId());
		carResponse.setBrand(car.getBrand());
		carResponse.setModel(car.getModel());
		
		return carResponse;
	}
	
	
}
