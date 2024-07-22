package com.practice.csa.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.exception.CarNotFoundByIdException;
import com.practice.csa.exception.ServiceNotFoundByIdException;
import com.practice.csa.mapper.ServiceMapper;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.requestDto.ServiceRequest;
import com.practice.csa.responseDto.CarResponse;
import com.practice.csa.responseDto.ServiceResponse;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<ServiceResponse>> addService(ServiceRequest serviceRequest) {
		com.practice.csa.entity.Service service = serviceMapper.mapToService(serviceRequest);
		
		service = serviceRepository.save(service);
		
		ServiceResponse serviceResponse = serviceMapper.mapToServiceResponse(service);
		
		ResponseStructure<ServiceResponse> responseStructure = new ResponseStructure<ServiceResponse>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Service Object created successfully!!");
		responseStructure.setData(serviceResponse);
		 
		return new ResponseEntity<ResponseStructure<ServiceResponse>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponse>> deleteByServiceId(int serviceId) {
//		Optional<com.practice.csa.entity.Service> optional = serviceRepository.findById(serviceId);
//		if (optional.isPresent()) {
//			com.practice.csa.entity.Service service = optional.get();
//			serviceRepository.delete(service);
//			ServiceResponse serviceResponse =  serviceMapper.mapToServiceResponse(service);
//			
//			ResponseStructure<ServiceResponse> responseStructure = new ResponseStructure<ServiceResponse>();
//			responseStructure.setStatuscode(HttpStatus.CREATED.value());
//			responseStructure.setMessage("Service Object deleted successfully!!");
//			responseStructure.setData(serviceResponse);
//			 
//			return new ResponseEntity<ResponseStructure<ServiceResponse>>(responseStructure, HttpStatus.CREATED);
//		}
//		throw new ServiceNotFoundByIdException("Service Object Not Found!!");
	
		return serviceRepository.findById(serviceId)
		        .map(service -> {
		            serviceRepository.delete(service);
		                return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<ServiceResponse>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Service Object deleted successfully!!")
		                    .setData(serviceMapper.mapToServiceResponse(service)));
		        })
		        .orElseThrow(() -> new ServiceNotFoundByIdException("Service object not found"));
	
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ServiceResponse>>> findAllService() {
//		List<com.practice.csa.entity.Service> service = serviceRepository.findAll();
//		List<ServiceResponse> serviceResponse = new ArrayList<ServiceResponse>(); 
//		for (com.practice.csa.entity.Service service1 : service) {
//			ServiceResponse  serviceResponse1 = serviceMapper.mapToServiceResponse(service1);
//			serviceResponse.add(serviceResponse1);
//		}
		List<ServiceResponse> responses = serviceRepository.findAll()
				.stream()
				.map(service -> serviceMapper.mapToServiceResponse(service))
				.toList();
		
		if (responses.isEmpty()) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(new ResponseStructure<List<ServiceResponse>>()
	                        .setStatuscode(HttpStatus.NOT_FOUND.value())
	                        .setMessage("No Service Objects found")
	                        .setData(responses));
	    } else {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseStructure<List<ServiceResponse>>()
	                        .setStatuscode(HttpStatus.OK.value())
	                        .setMessage("Car Objects found successfully!!")
	                        .setData(responses));
	    }
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponse>> findByServiceId(int serviceId) {
//		Optional<com.practice.csa.entity.Service> optional  = serviceRepository.findById(serviceId);
//		
//		if(optional.isPresent()) {
//			com.practice.csa.entity.Service service = optional.get();
//			ServiceResponse serviceResponse =  serviceMapper.mapToServiceResponse(service);
//			
//			ResponseStructure<ServiceResponse> responseStructure = new ResponseStructure<ServiceResponse>();
//			responseStructure.setStatuscode(HttpStatus.CREATED.value());
//			responseStructure.setMessage("Service Object found successfully!!");
//			responseStructure.setData(serviceResponse);
//			 
//			return new ResponseEntity<ResponseStructure<ServiceResponse>>(responseStructure, HttpStatus.CREATED);
//		}
//		else {
//			throw new ServiceNotFoundByIdException("Service Object Not Found!!");
//		}
		return serviceRepository.findById(serviceId)
		.map(service -> ResponseEntity
				 .status(HttpStatus.FOUND)
				 .body(new ResponseStructure<ServiceResponse>()
						 .setStatuscode(HttpStatus.FOUND.value())
						 .setMessage("Service Object found successfully!!")
						 .setData(serviceMapper.mapToServiceResponse(service))))
	.orElseThrow(() -> new ServiceNotFoundByIdException("Service object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponse>> updatedByServiceId(int id,ServiceRequest serviceRequest) {
//		Optional<com.practice.csa.entity.Service> optional = serviceRepository.findById(id);
//		if (optional.isPresent()) {
//			com.practice.csa.entity.Service existingService = optional.get();
//			
//			com.practice.csa.entity.Service service = serviceMapper.mapToService(serviceRequest);
//            service.setServiceId(existingService.getServiceId());
//			
//			service = serviceRepository.save(service);
//			
//			ResponseStructure<ServiceResponse> responseStructure = new ResponseStructure<ServiceResponse>();
//			responseStructure.setStatuscode(HttpStatus.CREATED.value());
//			responseStructure.setMessage("Service Object found successfully!!");
//			responseStructure.setData(serviceMapper.mapToServiceResponse(service));
//			 
//			return new ResponseEntity<ResponseStructure<ServiceResponse>>(responseStructure, HttpStatus.CREATED);
//		
//		} else {
//			throw new ServiceNotFoundByIdException("Service Object Not Found!!");
//		}
	
		return serviceRepository.findById(id)
		        .map(existingService -> {
		            // Update the existing car with the values from CarRequest
		            existingService.setServiceType(serviceRequest.getType());
		            existingService.setServiceCost(serviceRequest.getCost());
		            existingService.setServiceDescription(serviceRequest.getDescription());
		            

		            // Save the updated car
		            serviceRepository.save(existingService);

		            return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<ServiceResponse>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Service Object updated successfully!!")
		                    .setData(serviceMapper.mapToServiceResponse(existingService)));
		        })
		        .orElseThrow(() -> new ServiceNotFoundByIdException("Car object not found"));
	
	}

	
}
