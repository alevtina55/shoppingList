package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ShoppingCartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class CalculateShoppingCartPriceAction implements Action {

    private static final String ACTION_NAME = "Get shopping Cart price";
    private final ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public CalculateShoppingCartPriceAction(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter shopping cart id:");
        Long id = scanner.nextLong();

        BigDecimal response = shoppingCartItemService.calcItemsPrice(id);
        System.out.println("Price: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
