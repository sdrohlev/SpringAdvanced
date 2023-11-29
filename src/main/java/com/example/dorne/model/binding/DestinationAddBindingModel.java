package com.example.dorne.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class DestinationAddBindingModel {

    private String name;
    private String imageUrl;
    public DestinationAddBindingModel() {
    }

    @NotEmpty(message = "You must enter a destination")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Please enter a image url")
    @URL(message = "Please enter a valid image url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
