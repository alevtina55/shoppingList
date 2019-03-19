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
    private Action exitAction;

    @Autowired
    public ConsoleUIConfiguration(Action createProductAction, Action findProductByIdAction, Action exitAction) {
        this.createProductAction = createProductAction;
        this.findProductByIdAction = findProductByIdAction;
        this.exitAction = exitAction;
    }

    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(createProductAction);
        actions.add(findProductByIdAction);
        actions.add(exitAction);

        return new ConsoleUI(actions);
    }
}
