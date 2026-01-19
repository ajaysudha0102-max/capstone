package com.ey.dto.request;


import jakarta.validation.constraints.*;

public class RatingRequest {

    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    
    @NotNull
    private Long customerId;
    
    @NotNull
    private Long cleanerId;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @NotBlank(message = "Comment is required")
    private String comment;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCleanerId() {
		return cleanerId;
	}

	public void setCleanerId(Long cleanerId) {
		this.cleanerId = cleanerId;
	}

	
    

}