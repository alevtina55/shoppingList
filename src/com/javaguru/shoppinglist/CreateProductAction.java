package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;

    private final Validator validator = new Validator();

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean inputIsInvalid = true;
        String name;
        BigDecimal price = null;
        BigDecimal discount = null;

        do {
            System.out.println("Enter product name:");
            name = scanner.nextLine();
            try {
                validator.validateName(name);
                inputIsInvalid = false;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error. Try again!");
            }
        } while (inputIsInvalid);

        inputIsInvalid = true;
        do {
            System.out.println("Enter product price: ");
            try {
                price = new BigDecimal(scanner.nextLine());
                validator.validatePrice(price);
                inputIsInvalid = false;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error. Try again!");
            }
        } while (inputIsInvalid);

        System.out.println("Enter product category: ");
        String category = scanner.nextLine();

        inputIsInvalid = true;
        do {
            System.out.println("Enter product discount: ");
            try {
                discount = new BigDecimal(scanner.nextLine());
                validator.validateDiscount(discount);
                inputIsInvalid = false;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error. Try again!");
            }
        } while (inputIsInvalid);

        System.out.println("Enter product description: ");
        String description = scanner.nextLine();

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDiscount(discount);
        product.setDescription(description);

        try {
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
