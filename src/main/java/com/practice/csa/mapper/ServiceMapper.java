package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.Service;
import com.practice.csa.requestDto.ServiceRequest;
import com.practice.csa.responseDto.ServiceResponse;

@Component
public class ServiceMapper {

	public Service mapToService(ServiceRequest request) {
		Service service = new Service();
		
		service.setServiceType(request.getType());
		service.setServiceCost(request.getCost());
		service.setServiceDescription(request.getDescription());
		
		return service;
	}
	
	public ServiceResponse mapToServiceResponse(Service service) {
		ServiceResponse serviceResponse = new ServiceResponse();
		
		serviceResponse.setId(service.getServiceId());
		serviceResponse.setType(service.getServiceType());
		serviceResponse.setCost(service.getServiceCost());
		serviceResponse.setDescription(service.getServiceDescription());
		
		return serviceResponse;
	}

}
