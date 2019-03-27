package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ShoppingCartItemService;
import com.javaguru.shoppinglist.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteShoppingCartAction implements Action {
    private static final String ACTION_NAME = "Delete Shopping Cart";
    private final ShoppingCartItemService shoppingCartItemService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public DeleteShoppingCartAction(ShoppingCartItemService shoppingCartItemService,
                                    ShoppingCartService shoppingCartService) {
        this.shoppingCartItemService = shoppingCartItemService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter Shopping Cart id: ");
        Long id = scanner.nextLong();

        shoppingCartItemService.deleteShoppingCartItems(id);
        shoppingCartService.deleteShoppingCart(id);

        System.out.println("Shopping cart successfully deleted");
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
