package com.ey.dto.request;

import com.ey.enums.BookingStatus;

import jakarta.validation.constraints.NotNull;

public class BookingStatusUpdateRequest {
	 @NotNull(message = "Status is required")
	    private BookingStatus status;

	    public BookingStatus getStatus() {
	        return status;
	    }

	    public void setStatus(BookingStatus status) {
	        this.status = status;
	    }

	    
	    

}
