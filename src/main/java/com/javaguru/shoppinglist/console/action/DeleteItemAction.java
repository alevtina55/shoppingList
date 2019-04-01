package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ShoppingCartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteItemAction implements Action {

    private static final String ACTION_NAME = "Delete item";
    private final ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public DeleteItemAction(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter item id: ");
        Long id = scanner.nextLong();

        shoppingCartItemService.deleteItem(id);
        System.out.println("Shopping Cart Item successfully deleted");
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
