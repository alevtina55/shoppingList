package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";
    private static final int PRICE_SCALE = 2;

    private final ProductService productService;

    @Autowired
    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter product name:");
            String name = scanner.nextLine();
            System.out.println("Enter product price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine())
                    .setScale(PRICE_SCALE, BigDecimal.ROUND_HALF_UP);
            System.out.println("Enter product category: ");
            String category = scanner.nextLine();
            System.out.println("Enter product discount: ");
            BigDecimal discount = new BigDecimal(scanner.nextLine());
            System.out.println("Enter product description: ");
            String description = scanner.nextLine();

            BigDecimal actualPrice = productService.calculateActualPrice(price, discount);

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            product.setDiscount(discount);
            product.setDescription(description);
            product.setActualPrice(actualPrice);

            Long response = productService.create(product);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
