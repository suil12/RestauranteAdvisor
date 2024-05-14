package com.example.prueba2.model;


public class Restaurant {


    private String name, phone, address, category, photoUrl;

    private Float rating;

    public Restaurant(){

    }


    public Restaurant(String name, String phone, String category, String address, String photoUrl) {
       this.name = name;
       this.phone= phone;
       this.category= category;
       this.address= address;
       this.photoUrl = photoUrl;
    }

    /*// Métodos para agregar y calcular valoraciones de usuarios
    public void addRating(double rating) {
        ratings.add(rating);
    }

    public double calculateAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        } else {
            double sum = 0.0;
            for (Double rating : ratings) {
                sum += rating;
            }
            return sum / ratings.size();
        }
    }*/

    // Métodos getter y setter para los atributos
    public String getName() {
        return name;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}