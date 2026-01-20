package com.ey.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ey.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@NotNull(message = "Service date is required")
	@FutureOrPresent
	private LocalDate serviceDate;

	@NotNull(message = "Booking status is required")
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus; // CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED

	@PositiveOrZero(message = "Total price must be zero or positive")
	private double totalPrice;

	private LocalDateTime createdAt;
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address serviceAddress;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User customer;

	@ManyToOne
	@JoinColumn(name = "cleaner_id")
	private User cleaner;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private CleaningService service;

	@OneToOne(mappedBy = "booking")
	private Payment payment;

	@OneToOne(mappedBy = "booking")
	private Rating rating;

	public Booking() {
		super();
	}

	
	public Booking(Long bookingId, @NotNull(message = "Service date is required") LocalDate serviceDate,
			@NotNull(message = "Booking status is required") BookingStatus bookingStatus,
			@PositiveOrZero(message = "Total price must be zero or positive") double totalPrice,
			LocalDateTime createdAt, Address serviceAddress, User customer, User cleaner, CleaningService service,
			Payment payment, Rating rating) {
		super();
		this.bookingId = bookingId;
		this.serviceDate = serviceDate;
		this.bookingStatus = bookingStatus;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.serviceAddress = serviceAddress;
		this.customer = customer;
		this.cleaner = cleaner;
		this.service = service;
		this.payment = payment;
		this.rating = rating;
	}


	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(LocalDate serviceDate) {
		this.serviceDate = serviceDate;
	}

	public  Address getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(Address address) {
		this.serviceAddress = address;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	public CleaningService getService() {
		return service;
	}

	public void setService(CleaningService service) {
		this.service = service;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}




}
