package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.entity.Car;
import com.practice.csa.requestDto.CarRequest;
import com.practice.csa.responseDto.CarResponse;
import com.practice.csa.utility.ResponseStructure;

public interface CarService {

	public ResponseEntity<ResponseStructure<CarResponse>> addCar(CarRequest car);
	
	public ResponseEntity<ResponseStructure<CarResponse>>  deleteByCarId(int carId);
	
	public ResponseEntity<ResponseStructure<List<CarResponse>>> findAllCar();
	
	public ResponseEntity<ResponseStructure<CarResponse>> findByCarId(int carId);
	
	public ResponseEntity<ResponseStructure<CarResponse>> updatedByCarId(int id, CarRequest carRequest);
}
