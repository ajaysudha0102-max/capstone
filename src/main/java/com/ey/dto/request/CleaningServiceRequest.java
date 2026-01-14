package com.ey.dto.request;

import jakarta.validation.constraints.*;

public class CleaningServiceRequest {

    @NotBlank(message = "Service name is required")
    @Size(min = 3, max = 100, message = "Service name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Service description is required")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    @Positive(message = "Price must be greater than 0")
    private double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
