package com.example.prueba2.model;

import java.util.ArrayList;


public class Review {
    private String name;
    private String date;
    private String score;
    private String title;
    private String description;
    private String photoUrl;



    public Review(){}
    public Review(String name, String date, String score, String title, String description) {
        this.name = name;
        this.date = date;
        this.score = score;
        this.title = title;
        this.description= description;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String address) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}