package com.example.foodrocket;

import java.util.ArrayList;

public class MenuItem {
    private int id;
    private String name;
    private String description;
    private int category_id;
    private double price;
    private String image_url;
    private ArrayList<ItemExtra> extras;

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

//    public ArrayList<ItemExtra> getExtras() {
//        return extras;
//    }

//    public void setExtras(ArrayList<ItemExtra> extras) {
//        this.extras = extras;
//    }
}
