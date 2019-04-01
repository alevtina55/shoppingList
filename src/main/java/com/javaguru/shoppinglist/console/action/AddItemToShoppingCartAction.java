package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ShoppingCartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddItemToShoppingCartAction implements Action {
    private static final String ACTION_NAME = "Add item to the shopping cart";
    private final ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public AddItemToShoppingCartAction(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter Shopping Cart id: ");
        Long shoppingCartId = scanner.nextLong();
        System.out.println("Please, enter Product id: ");
        Long productId = scanner.nextLong();
        System.out.println("Please, enter count of products: ");
        Integer count = scanner.nextInt();

        Long response = shoppingCartItemService
                .createShoppingCartItem(shoppingCartId, productId, count);
        System.out.println("Item id: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
