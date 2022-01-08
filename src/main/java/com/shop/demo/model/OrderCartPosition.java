package com.shop.demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderCartPosition {
    private FoodProduct product;
    private int quantity;
    private double sumProducts;

    public BigDecimal getSumProducts() {
        sumProducts = quantity * product.getPrice();
        return BigDecimal.valueOf(sumProducts).setScale(2, RoundingMode.DOWN);
    }

    public OrderCartPosition(FoodProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderCartPosition() {
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void minusQuantity() {
        this.quantity--;
    }

    public FoodProduct getProduct() {
        return product;
    }

    public void setBook(FoodProduct product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

}
