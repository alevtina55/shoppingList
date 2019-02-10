package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;


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
            BigDecimal price = new BigDecimal(scanner.nextLine());
            System.out.println("Enter product category: ");
            String category = scanner.nextLine();
            System.out.println("Enter product discount: ");
            BigDecimal discount = new BigDecimal(scanner.nextLine());
            System.out.println("Enter product description: ");
            String description = scanner.nextLine();

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            product.setDiscount(discount);
            product.setDescription(description);

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
