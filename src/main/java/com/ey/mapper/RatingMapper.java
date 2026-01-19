package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.RatingRequest;
import com.ey.dto.response.RatingResponse;
import com.ey.entity.Rating;

@Component
public class RatingMapper {
	
	public static Rating requestToEntity(RatingRequest request)
	{
		Rating rating = new Rating();
		
		rating.setRating(request.getRating());
		rating.setComment(request.getComment());
		return rating;
	}
 public static RatingResponse entityToResponse(Rating rating) {
	 RatingResponse response = new RatingResponse();
	   response.setId(rating.getId());
	   response.setRating(rating.getRating());
	   response.setComment(rating.getComment());
	   
	   response.setCustomerId(rating.getCustomer().getId());
	   response.setCleanerId(rating.getCleaner().getId());
	   response.setBookingId(rating.getBooking().getBookingId());
	   return response;
 }
}
