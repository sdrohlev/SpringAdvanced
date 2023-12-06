package com.example.dorne.model.binding;

import jakarta.validation.constraints.NotEmpty;

public class SearchDestinationBindingModel {

    private String destination;
    private String category;

    public SearchDestinationBindingModel() {
    }

    @NotEmpty(message = "Please select a destination before searching!")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @NotEmpty(message = "Please select a category before searching!")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
