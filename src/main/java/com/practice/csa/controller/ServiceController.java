package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestDto.ServiceRequest;
import com.practice.csa.responseDto.ServiceResponse;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class ServiceController {

	@Autowired
	private ServiceService serviceService;
	
	@PostMapping("/services")
	public ResponseEntity<ResponseStructure<ServiceResponse>> addService(@RequestBody ServiceRequest service) {
		return serviceService.addService(service);	
	}
	
	@DeleteMapping("/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponse>> deleteByServiceId(@PathVariable int serviceId) {
		return serviceService.deleteByServiceId(serviceId);
	}
	
	@GetMapping("/services")
	public ResponseEntity<ResponseStructure<List<ServiceResponse>>> findAllService() {
		return serviceService.findAllService();
	}
	
	@GetMapping("/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponse>> findByServiceId(@PathVariable int serviceId) {
		return serviceService.findByServiceId(serviceId);
	}
	
	@PutMapping("/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponse>> updateByServiceId(@PathVariable int serviceId,@RequestBody ServiceRequest serviceRequest) {
		return serviceService.updatedByServiceId(serviceId,serviceRequest);
	}	
	
}
