package com.shop.demo.model;

public class FoodProduct {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String pathImage;

    public FoodProduct(int id, String name, double price, int quantity, String pathImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pathImage = pathImage;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
