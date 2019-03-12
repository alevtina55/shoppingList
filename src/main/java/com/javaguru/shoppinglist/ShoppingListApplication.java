package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.configuration.AppConfiguration;
import com.javaguru.shoppinglist.console.ConsoleUI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ShoppingListApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
        consoleUI.start();
    }
}
