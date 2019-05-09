package com.example.projectsmartershopping.model;

import android.graphics.drawable.Icon;

public class Fashion {

    private String image_url;
    private String title;
    private String description;
    private double price;
    private double rate;
    private int id;

    private boolean isSelected;
    private String fation_girl;

    public Fashion(boolean isSelected, String fation_girl) {
        this.isSelected = isSelected;
        this.fation_girl = fation_girl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getFation_girl() {
        return fation_girl;
    }

    public void setFation_girl(String fation_girl) {
        this.fation_girl = fation_girl;
    }



    public Fashion() {

    }

    public Fashion(String image_url, String title, String description,  double price, double rate, int id) {
        this.image_url = image_url;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.id = id;
    }

    public Fashion(String image, String description, double price, int id) {
        this.image_url = image;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public Fashion(String image, String description, double price, double rate, int id) {
        this.image_url = image;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.id = id;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
