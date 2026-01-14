package com.ey.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ratings")
public class Rating {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Min(value = 1, message = "Rating must be at least 1")
	    @Max(value = 5, message = "Rating must be at most 5")
	    private int rating;

	    @NotBlank(message = "Comment is required")
	    private String comment;

	    @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private User customer;

	    @ManyToOne
	    @JoinColumn(name = "cleaner_id", nullable = false)
	    private User cleaner;

	    @OneToOne
	    @JoinColumn(name = "booking_id", nullable = false)
	    private Booking booking;

	    private LocalDateTime createdAt;

		public Rating() {
			super();
		}

		public Rating(Long id,
				@Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must be at most 5") int rating,
				@NotBlank(message = "Comment is required") String comment, User customer, User cleaner, Booking booking,
				LocalDateTime createdAt) {
			super();
			this.id = id;
			this.rating = rating;
			this.comment = comment;
			this.customer = customer;
			this.cleaner = cleaner;
			this.booking = booking;
			this.createdAt = createdAt;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public User getCustomer() {
			return customer;
		}

		public void setCustomer(User customer) {
			this.customer = customer;
		}

		public User getCleaner() {
			return cleaner;
		}

		public void setCleaner(User cleaner) {
			this.cleaner = cleaner;
		}

		public Booking getBooking() {
			return booking;
		}

		public void setBooking(Booking booking) {
			this.booking = booking;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	    
	    
}
