package com.ey.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ey.dto.request.BookingRequest;
import com.ey.dto.response.BookingResponse;
import com.ey.dto.response.CleanerBookingDetailsResponse;
import com.ey.entity.Address;
import com.ey.entity.Booking;
import com.ey.entity.CleaningService;
import com.ey.entity.Payment;
import com.ey.entity.User;

@Component
public class BookingMapper {
	public static Booking requestToEntity(BookingRequest request) {
		Booking booking = new Booking();
		
		booking.setServiceDate(request.getServiceDate());
// set bookingstatus in service
		booking.setCreatedAt(LocalDateTime.now());
		return booking;
	
	}
	public static BookingResponse entityToResponse(Booking booking) {
		BookingResponse response = new BookingResponse();
		response.setBookingId(booking.getBookingId());
		response.setCustomerId(booking.getCustomer().getId());
		response.setServiceId(booking.getService().getId());

		response.setAddressId(booking.getServiceAddress().getId());
		response.setServiceDate(booking.getServiceDate());
		response.setBookingStatus(booking.getBookingStatus());
		// response.setPaymentStatus(booking.getPayment().getPaymentStatus());
		response.setTotalPrice(booking.getTotalPrice());
		return response;
	}


    public static CleanerBookingDetailsResponse toCleanerBookingResponse(Booking booking) {

    	CleanerBookingDetailsResponse res = new CleanerBookingDetailsResponse();

        res.setBookingId(booking.getBookingId());
        res.setServiceDate(booking.getServiceDate());
        res.setBookingStatus(booking.getBookingStatus());

        User customer = booking.getCustomer();
        res.setCustomerId(customer.getId());
        res.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        res.setCustomerPhone(customer.getPhoneNumber());

        CleaningService service = booking.getService();
        res.setServiceId(service.getId());
        res.setServiceName(service.getName());
        res.setServicePrice(service.getPrice());

        Address address = booking.getServiceAddress();
        res.setAddressId(address.getId());
        res.setHouseNumber(address.getHouseNumber());
        res.setStreet(address.getStreet());
        res.setCity(address.getCity());
        res.setState(address.getState());
        res.setPincode(address.getPincode());

        Payment payment = booking.getPayment();
        if (payment != null) {
            res.setPaymentId(payment.getPaymentId());
            res.setAmount(payment.getAmount());
            res.setPaymentStatus(payment.getPaymentStatus());
            res.setPaymentMethod(payment.getPaymentMethod());
        }

        return res;
    }
}
