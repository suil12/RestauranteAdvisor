package com.example.prueba2.model;

public class Restaurant {
    private String name;
    private String address;
    private String phone;
    private String email;
    private int numberOfReviews;
    private float averageRating;
    private String imageUrl;

    public Restaurant() {
        // Constructor vacío requerido por Firebase Firestore
    }

    public Restaurant(String name, String address, String phone, String email, int numberOfReviews, float averageRating, String imageUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.numberOfReviews = numberOfReviews;
        this.averageRating = averageRating;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
