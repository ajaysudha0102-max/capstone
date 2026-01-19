package com.ey.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ey.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50)
	private String lastName;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid phone number")
	private String phoneNumber;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column(unique = true, nullable = false)
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6)
	private String password;

	@NotNull(message = "Role is required")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
    private LocalDateTime createdAt;
    

    @OneToMany(mappedBy = "customer")
    private List<Booking> customerBookings;

    @OneToMany(mappedBy = "cleaner")
    private List<Booking> cleanerBookings;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Rating> givenRatings;

    @OneToMany(mappedBy = "cleaner")
    private List<Rating> receivedRatings;

	public User() {
		super();
	}

	public User(Long id, @NotBlank(message = "First name is required") @Size(min = 2, max = 50) String firstName,
			@NotBlank(message = "Last name is required") @Size(min = 2, max = 50) String lastName,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid phone number") String phoneNumber,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Password is required") @Size(min = 6) String password,
			@NotNull(message = "Role is required") @NotBlank(message = "Role is required") UserRole role, LocalDateTime createdAt,
			List<Booking> customerBookings, List<Booking> cleanerBookings, List<Address> addresses,
			List<Rating> givenRatings, List<Rating> receivedRatings) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.customerBookings = customerBookings;
		this.cleanerBookings = cleanerBookings;
		this.addresses = addresses;
		this.givenRatings = givenRatings;
		this.receivedRatings = receivedRatings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public  UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Booking> getCustomerBookings() {
		return customerBookings;
	}

	public void setCustomerBookings(List<Booking> customerBookings) {
		this.customerBookings = customerBookings;
	}

	public List<Booking> getCleanerBookings() {
		return cleanerBookings;
	}

	public void setCleanerBookings(List<Booking> cleanerBookings) {
		this.cleanerBookings = cleanerBookings;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Rating> getGivenRatings() {
		return givenRatings;
	}

	public void setGivenRatings(List<Rating> givenRatings) {
		this.givenRatings = givenRatings;
	}

	public List<Rating> getReceivedRatings() {
		return receivedRatings;
	}

	public void setReceivedRatings(List<Rating> receivedRatings) {
		this.receivedRatings = receivedRatings;
	}

	

}
