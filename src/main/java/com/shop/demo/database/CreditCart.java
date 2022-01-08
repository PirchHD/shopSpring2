package com.shop.demo.database;


import com.shop.demo.model.OrderCartPosition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CreditCart {

    private static List<CreditCart> creditCartList = new ArrayList<>();
    private String name;
    private String lastname;
    private String pin;

    public CreditCart() {
        creditCartList.add(new CreditCart("Szymon",
                "Prochal",
                "1234"));

        creditCartList.add(new CreditCart("Magda",
                "Prochal",
                "1111"));
    }

    public CreditCart(String name, String lastname, String pin) {
        this.name = name;
        this.lastname = lastname;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public static boolean dataIsExist(String name, String lastname, String pin){

        for(CreditCart creditCart : creditCartList){
            if(creditCart.name.equals(name)
            && creditCart.lastname.equals(lastname)
            && creditCart.pin.equals(pin)) {
                return true;
            }
        }

        return false;
    }

    public CreditCart getByPin(String pin) {
        for(CreditCart creditCart : creditCartList){
            if(creditCart.pin.equals(pin)) {
                return creditCart;
            }
        }

        return null;
    }
}
