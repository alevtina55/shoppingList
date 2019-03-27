package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {
    private Action createProductAction;
    private Action findProductByIdAction;
    private Action createShoppingCartAction;
    private Action addItemToShoppingCartAction;
    private Action findAllItemsInShoppingCartAction;
    private Action calculateShoppingCartPriceAction;
    private Action deleteItemAction;
    private Action deleteShoppingCartAction;
    private Action exitAction;

    @Autowired
    public ConsoleUIConfiguration(Action createProductAction, Action findProductByIdAction,
                                  Action createShoppingCartAction,
                                  Action addItemToShoppingCartAction,
                                  Action findAllItemsInShoppingCartAction,
                                  Action calculateShoppingCartPriceAction, Action deleteItemAction,
                                  Action deleteShoppingCartAction, Action exitAction) {
        this.createProductAction = createProductAction;
        this.findProductByIdAction = findProductByIdAction;
        this.createShoppingCartAction = createShoppingCartAction;
        this.addItemToShoppingCartAction = addItemToShoppingCartAction;
        this.findAllItemsInShoppingCartAction = findAllItemsInShoppingCartAction;
        this.calculateShoppingCartPriceAction = calculateShoppingCartPriceAction;
        this.deleteItemAction = deleteItemAction;
        this.deleteShoppingCartAction = deleteShoppingCartAction;
        this.exitAction = exitAction;
    }

    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(createProductAction);
        actions.add(findProductByIdAction);
        actions.add(createShoppingCartAction);
        actions.add(addItemToShoppingCartAction);
        actions.add(findAllItemsInShoppingCartAction);
        actions.add(calculateShoppingCartPriceAction);
        actions.add(deleteItemAction);
        actions.add(deleteShoppingCartAction);
        actions.add(exitAction);

        return new ConsoleUI(actions);
    }
}
