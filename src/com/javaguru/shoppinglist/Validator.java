package com.javaguru.shoppinglist;

import java.math.BigDecimal;

public class Validator {
    public void validateName(String name) throws ValidationException {
        if (name.trim().length() < 3) {
            throw new ValidationException("Name is too short");
        }

        if (name.trim().length() > 32) {
            throw new ValidationException("Name is to long");
        }
    }

    public void validatePrice(BigDecimal price) throws ValidationException {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Price should be greater than 0");
        }
    }

    public void validateDiscount(BigDecimal discount) throws ValidationException {
        if (discount.compareTo(BigDecimal.ZERO) < 0 || discount.compareTo(new BigDecimal(100)) > 0) {
            throw new ValidationException("Discount should not be less than 0 and greater than 100");
        }
    }

}
