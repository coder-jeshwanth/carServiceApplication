package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestDto.ServiceRequest;
import com.practice.csa.responseDto.ServiceResponse;
import com.practice.csa.utility.ResponseStructure;

public interface ServiceService {
	
	public ResponseEntity<ResponseStructure<ServiceResponse>> addService(ServiceRequest serviceRequest);
	
	public ResponseEntity<ResponseStructure<ServiceResponse>>  deleteByServiceId(int serviceId);
	
	public ResponseEntity<ResponseStructure<List<ServiceResponse>>> findAllService();
	
	public ResponseEntity<ResponseStructure<ServiceResponse>> findByServiceId(int serviceId);
	
	public ResponseEntity<ResponseStructure<ServiceResponse>> updatedByServiceId(int id, ServiceRequest serviceRequest);

}
