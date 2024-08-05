package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.entity.Car;
import com.practice.csa.requestDto.CarRequest;
import com.practice.csa.responseDto.CarResponse;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

//@Controller
//@ResponseBody
@RestController
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping("/cars")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarResponse>> addCar(@RequestBody CarRequest car) {
		return carService.addCar(car);	
	}
	
	@DeleteMapping("/cars/{carId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarResponse>> deleteById(@PathVariable int carId) {
		return carService.deleteByCarId(carId);
	}
	
	@GetMapping("/cars")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<List<CarResponse>>> findAllCar() {
		return carService.findAllCar();
	}
	
	@GetMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponse>> findByCarId(@PathVariable int carId) {
		return carService.findByCarId(carId);
	}
	
	@PutMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponse>> updateById(@PathVariable int carId,@RequestBody CarRequest carRequest) {
		return carService.updatedByCarId(carId,carRequest);
	}

	

}
