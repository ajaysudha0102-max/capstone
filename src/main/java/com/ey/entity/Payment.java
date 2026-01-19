package com.ey.entity;

import java.time.LocalDateTime;

import com.ey.enums.PaymentMethod;
import com.ey.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Entity
@Table(name = "payments")
public class Payment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long paymentId;

	    @Positive(message = "Payment amount must be greater than 0")
	    private double amount;

	    @NotNull(message = "Payment method is required (COD / CARD / UPI)")
	    @Enumerated(EnumType.STRING)
	    private PaymentMethod paymentMethod;

	    @NotNull(message = "Payment status is required (PENDING / SUCCESS / FAILED)")
	    @Enumerated(EnumType.STRING)

	    private PaymentStatus paymentStatus;
	    
	    private LocalDateTime paymentDate;
	    
	    @OneToOne
	    @JoinColumn(name = "bookingId", nullable = false)
	    private Booking booking;

		public Payment(Long paymentId, @Positive(message = "Payment amount must be greater than 0") double amount,
				@NotBlank(message = "Payment method is required (COD / CARD / UPI)") PaymentMethod paymentMethod,
				@NotBlank(message = "Payment status is required (PENDING / SUCCESS / FAILED)") PaymentStatus paymentStatus,
				LocalDateTime paymentDate, Booking booking) {
			super();
			this.paymentId = paymentId;
			this.amount = amount;
			this.paymentMethod = paymentMethod;
			this.paymentStatus = paymentStatus;
			this.paymentDate = paymentDate;
			this.booking = booking;
		}

		public Payment() {
			super();
		}

		public Long getPaymentId() {
			return paymentId;
		}

		public void setPaymentId(Long paymentId) {
			this.paymentId = paymentId;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public PaymentMethod getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public PaymentStatus getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(PaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public LocalDateTime getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(LocalDateTime paymentDate) {
			this.paymentDate = paymentDate;
		}

		public Booking getBooking() {
			return booking;
		}

		public void setBooking(Booking booking) {
			this.booking = booking;
		}
		
	    


}
