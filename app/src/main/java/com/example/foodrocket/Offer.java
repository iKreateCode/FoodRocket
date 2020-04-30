package com.example.foodrocket;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    private int id;
    private String name;
    private String description;
    private double price;
    private ArrayList<OfferItem> offer_items;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<OfferItem> getOffer_items() {
        return offer_items;
    }

    public void setOffer_items(ArrayList<OfferItem> offer_items) {
        this.offer_items = offer_items;
    }
}
