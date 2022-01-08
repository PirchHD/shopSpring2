package com.shop.demo.controllers;

import com.shop.demo.database.DB;
import com.shop.demo.database.CreditCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommonController{

    @Autowired
    DB database;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("products", this.database.getProducts());
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }


    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buyForm() {
        return "/buy";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String transaction(@RequestParam String name, @RequestParam String lastname,
                        @RequestParam String pin, Model model) {
        CreditCart creditCart = new CreditCart();

        if(creditCart.dataIsExist(name, lastname, pin)){
            model.addAttribute("credit", creditCart.getByPin(pin));
            model.addAttribute("result", "Zgadza się !");
            this.database.getOrderCart().clear();
            return "/buy";
        }else{
            model.addAttribute("result", "cos poszło nie tak - Nie prawidłowe dane");
            this.database.takeProductsFromCartToStore();
            this.database.getOrderCart().clear();
            return "/buy";
        }
    }

}
