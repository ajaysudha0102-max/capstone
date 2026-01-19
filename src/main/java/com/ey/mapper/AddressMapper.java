package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.AddressRequest;
import com.ey.dto.response.AddressResponse;
import com.ey.entity.Address;

@Component
public class AddressMapper {
	
	public static Address requestToEntity(AddressRequest request) {
		Address address = new Address();
		address.setHouseNumber(request.getHouseNumber());
		address.setStreet(request.getStreet());
		address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        return address;
		
		
	}
	public static AddressResponse entityToResponse(Address address) {
		 AddressResponse response = new AddressResponse();
	        response.setId(address.getId());
	        response.setUserId(address.getUser().getId());
	        response.setHouseNumber(address.getHouseNumber());
	        response.setStreet(address.getStreet());
	        response.setCity(address.getCity());
	        response.setState(address.getState());
	        response.setPincode(address.getPincode());
	        
	        return response;

}
}