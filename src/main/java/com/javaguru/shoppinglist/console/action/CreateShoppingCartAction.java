package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateShoppingCartAction implements Action {
    private static final String ACTION_NAME = "Create Shopping Cart";
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public CreateShoppingCartAction(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name of the shopping cart: ");
        String name = scanner.nextLine();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName(name);

        Long response = shoppingCartService.saveShoppingCart(shoppingCart);
        System.out.println("Response: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
