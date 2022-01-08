package com.shop.demo.controllers;

import com.shop.demo.database.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    @Autowired
    DB database;

    @RequestMapping(value = "cart/add/{bookId}")
    public String addBookToCart(@PathVariable Integer bookId) {
        this.database.addProductToCart(bookId);
        return "redirect:/main";
    }

    @RequestMapping(value = "cart/delete/{bookId}")
    public String deleteBookToCart(@PathVariable Integer bookId) {
        this.database.removeProductInCart(bookId);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart", this.database.getOrderCart());
        model.addAttribute("sum", this.database.getSum());
        return "cart";
    }


}
