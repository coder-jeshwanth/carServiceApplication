package com.practice.csa.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Car;
import com.practice.csa.exception.CarNotFoundByIdException;
import com.practice.csa.mapper.CarMapper;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.requestDto.CarRequest;
import com.practice.csa.responseDto.CarResponse;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarMapper carMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> addCar(CarRequest carRequest) {
		
		Car car = carMapper.mapToCar(carRequest);
		
		car = carRepository.save(car);
		
		CarResponse carResponse = carMapper.mapToCarResponse(car);
		
		ResponseStructure<CarResponse> responseStructure = new ResponseStructure<CarResponse>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Car Object created successfully!!");
		responseStructure.setData(carResponse);
		 
		return new ResponseEntity<ResponseStructure<CarResponse>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> deleteByCarId(int carId) {
//		Optional<Car> optional = carRepository.findById(carId);
//		if (optional.isPresent()) {
//			Car car = optional.get();
//			carRepository.delete(car);
//			CarResponse carResponse =  carMapper.mapToCarResponse(car);
//			
//			ResponseStructure<CarResponse> responseStructure = new ResponseStructure<CarResponse>();
//			responseStructure.setStatuscode(HttpStatus.OK.value());
//			responseStructure.setMessage("Car Object deleted successfully!!");
//			responseStructure.setData(carResponse);
//			 
//			return new ResponseEntity<ResponseStructure<CarResponse>>(responseStructure, HttpStatus.OK);
//		}
//		throw new CarNotFoundByIdException("Car Object Not Found!!");
		

		return carRepository.findById(carId)
		        .map(car -> {
		            carRepository.delete(car);
		                return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<CarResponse>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Car Object deleted successfully!!")
		                    .setData(carMapper.mapToCarResponse(car)));
		        })
		        .orElseThrow(() -> new CarNotFoundByIdException("Car object not found"));
	
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CarResponse>>> findAllCar() {
//		List<Car> car = carRepository.findAll();
//		List<CarResponse> carResponse = new ArrayList<CarResponse>(); 
//		for (Car car2 : car) {
//			CarResponse  carResponse1 = carMapper.mapToCarResponse(car2);
//			carResponse.add(carResponse1);
//		}
		
		List<CarResponse> responses = carRepository.findAll()
	            .stream()
	            .map(car -> carMapper.mapToCarResponse(car))
	            .toList();

	    if (responses.isEmpty()) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(new ResponseStructure<List<CarResponse>>()
	                        .setStatuscode(HttpStatus.NOT_FOUND.value())
	                        .setMessage("No Car Objects found")
	                        .setData(responses));
	    } else {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseStructure<List<CarResponse>>()
	                        .setStatuscode(HttpStatus.OK.value())
	                        .setMessage("Car Objects found successfully!!")
	                        .setData(responses));
	    }
			 
	}

	

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> findByCarId(int carId) {
//		Optional<Car> optional  = carRepository.findById(carId);
//		
//		if(optional.isPresent()) {
//			Car car = optional.get();
//			CarResponse carResponse =  carMapper.mapToCarResponse(car);
//			
//			 ResponseStructure<CarResponse> responseStructure = new ResponseStructure<CarResponse>();
//			 responseStructure.setStatuscode(HttpStatus.FOUND.value());
//			 responseStructure.setMessage("Actor Object found successfully!!");
//			 responseStructure.setData(carResponse);
//			 
//			 return new ResponseEntity<ResponseStructure<CarResponse>>(responseStructure, HttpStatus.FOUND);
//
//		}
//		else {
//			throw new CarNotFoundByIdException("Car Object Not Found!!");
//		}
		
		return carRepository.findById(carId)
				.map(car -> ResponseEntity
							 .status(HttpStatus.FOUND)
							 .body(new ResponseStructure<CarResponse>()
									 .setStatuscode(HttpStatus.FOUND.value())
									 .setMessage("Car Object found successfully!!")
									 .setData(carMapper.mapToCarResponse(car))))
				.orElseThrow(() -> new CarNotFoundByIdException("Car object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> updatedByCarId(int id, CarRequest carRequest) {
//		Optional<Car> optional = carRepository.findById(id);
//		if (optional.isPresent()) {
//			Car existingCar = optional.get();
//			
//			Car car = carMapper.mapToCar(carRequest);
//            car.setCarId(existingCar.getCarId());
//			
//			car = carRepository.save(car);
//			
//			CarResponse response = carMapper.mapToCarResponse(car);
//			
//			ResponseStructure<CarResponse> responseStructure = new ResponseStructure<CarResponse>();
//			responseStructure.setStatuscode(HttpStatus.OK.value());
//			responseStructure.setMessage("Car Object Updated successfully!!");
//			responseStructure.setData(response);
//			return new ResponseEntity<ResponseStructure<CarResponse>>(responseStructure, HttpStatus.OK);
//		
//		} else {
//			throw new CarNotFoundByIdException("Car Object Not Found!!");
//		}

		
		 return carRepository.findById(id)
			        .map(existingCar -> {
			            // Update the existing car with the values from CarRequest
			            existingCar.setModel(carRequest.getModel());
			            existingCar.setBrand(carRequest.getBrand());
			            

			            // Save the updated car
			            carRepository.save(existingCar);

			            return ResponseEntity
			                .status(HttpStatus.OK)
			                .body(new ResponseStructure<CarResponse>()
			                    .setStatuscode(HttpStatus.OK.value())
			                    .setMessage("Car Object updated successfully!!")
			                    .setData(carMapper.mapToCarResponse(existingCar)));
			        })
			        .orElseThrow(() -> new CarNotFoundByIdException("Car object not found"));
	} 

}
