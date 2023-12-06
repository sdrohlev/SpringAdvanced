package com.example.dorne.model.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ReviewAddBindingModel {

    private String review;

    @NotEmpty(message = "Please write a review before submitting!")
    @Size(min = 20, message = "The review is too short - make it longer")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
