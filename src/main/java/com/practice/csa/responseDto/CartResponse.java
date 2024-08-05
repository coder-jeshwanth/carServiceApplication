package com.practice.csa.responseDto;

import java.util.List;

public class CartResponse {

	private int cartId;
	
	List<ServiceResponse> serviceResponse;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<ServiceResponse> getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(List<ServiceResponse> serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	
	
	
	
}
