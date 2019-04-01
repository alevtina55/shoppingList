package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.service.ShoppingCartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class FindAllItemsInShoppingCartAction implements Action {
    private static final String ACTION_NAME = "Find all items in shopping cart";
    private final ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public FindAllItemsInShoppingCartAction(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter Shopping Cart id: ");
        Long shoppingCartId = scanner.nextLong();

        List<ShoppingCartItem> response = shoppingCartItemService
                .findItemsByCartId(shoppingCartId);
        System.out.println("Response: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
