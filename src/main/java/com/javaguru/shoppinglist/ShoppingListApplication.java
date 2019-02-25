package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.console.action.Action;
import com.javaguru.shoppinglist.console.action.CreateProductAction;
import com.javaguru.shoppinglist.console.action.ExitAction;
import com.javaguru.shoppinglist.console.action.FindProductByIdAction;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.DefaultProductService;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.rule.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductUniqueNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductValidationRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ShoppingListApplication {

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();

        ProductDiscountValidationRule productDiscountValidationRule =
                new ProductDiscountValidationRule();
        ProductNameValidationRule productNameValidationRule =
                new ProductNameValidationRule();
        ProductPriceValidationRule productPriceValidationRule =
                new ProductPriceValidationRule();
        ProductUniqueNameValidationRule productUniqueNameValidationRule =
                new ProductUniqueNameValidationRule(productRepository);

        Set<ProductValidationRule> validationRules = new HashSet<>();
        validationRules.add(productDiscountValidationRule);
        validationRules.add(productNameValidationRule);
        validationRules.add(productPriceValidationRule);
        validationRules.add(productUniqueNameValidationRule);

        ProductValidationService productValidationService =
                new ProductValidationService(validationRules);
        ProductService productService =
                new DefaultProductService(productRepository, productValidationService);

        Action exitAction = new ExitAction();
        Action createUserAction = new CreateProductAction(productService);
        Action findUserByIdAction = new FindProductByIdAction(productService);

        List<Action> actions = new ArrayList<>();
        actions.add(findUserByIdAction);
        actions.add(createUserAction);
        actions.add(exitAction);

        ConsoleUI ui = new ConsoleUI(actions);
        ui.start();
    }
}
