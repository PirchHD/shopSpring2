package com.shop.demo.database;

import com.shop.demo.model.FoodProduct;
import com.shop.demo.model.OrderCartPosition;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.List;

@Component
public class DB {

    private List<FoodProduct> products = new ArrayList<>();
    private List<OrderCartPosition> orderCart = new ArrayList<>();

    public List<OrderCartPosition> getOrderCart() {
        return orderCart;
    }

    public void addProductToCart(OrderCartPosition orderCartPosition){
        orderCart.add(orderCartPosition);
    }

    public void removeProductInCart(OrderCartPosition orderCartPosition){
        orderCart.remove(orderCartPosition);
    }

    public DB() {
        this.products.add(
                new FoodProduct(1,
                        "Brzoskwinia",
                        12.90,
                        10, "brzoskwinia.png"));

        this.products.add(new FoodProduct(2,
                "Jabłko",
                55.20,
                10,
                "jablko.jpg"));

        this.products.add(new FoodProduct(3,
                "Banany",
                43.91,
                15,
                "banan.png"));
    }

    public List<FoodProduct> getProducts() {
        return products;
    }

    public FoodProduct getProductById(int bookId) {
        for(FoodProduct product : this.products) {
            if(product.getId() == bookId) {
                return product;
            }
        }
        return null;
    }

    public OrderCartPosition getProductInCartById(int bookId) {
        for(OrderCartPosition product : this.orderCart) {
            if(product.getProduct().getId() == bookId) {
                return product;
            }
        }
        return null;
    }

    public BigDecimal getSum() {
        double sum = 0.0;
        for(OrderCartPosition orderPosition : this.getOrderCart()) {
            sum += orderPosition.getQuantity() * orderPosition.getProduct().getPrice();
        }

        return BigDecimal.valueOf(sum).setScale(2, RoundingMode.DOWN);
    }

    public void addProductToCart(int bookId) {

        FoodProduct product = getProductById(bookId);
        if(product == null)
            return;

        if(!(product.getQuantity() > 0)) {
            return;
        }else{
            product.setQuantity(product.getQuantity() - 1);
        }

        for(OrderCartPosition orderCartPosition : this.orderCart) {
            if(orderCartPosition.getProduct().getId() == bookId) {
                orderCartPosition.incrementQuantity();
                return;
            }
        }

        OrderCartPosition orderCartPosition = new OrderCartPosition(product, 1);

        addProductToCart(orderCartPosition);
    }

    public void removeProductInCart(int bookId) {
        OrderCartPosition productPosition = getProductInCartById(bookId);
        FoodProduct product = getProductById(bookId);

        for(OrderCartPosition orderCartPosition : this.orderCart) {
            if(orderCartPosition.getProduct().getId() == bookId) {
                orderCartPosition.minusQuantity();
                product.incrementQuantity();
                if(orderCartPosition.getQuantity() == 0)
                    removeProductInCart(productPosition);

                return;
            }
        }
    }

    public void takeProductsFromCartToStore() {
        for(FoodProduct product : this.products) {
            for (OrderCartPosition orderCartPosition : this.orderCart) {
                if(orderCartPosition.getProduct().getId() == product.getId())
                    product.setQuantity(product.getQuantity() + orderCartPosition.getQuantity());
            }
        }
    }
}
