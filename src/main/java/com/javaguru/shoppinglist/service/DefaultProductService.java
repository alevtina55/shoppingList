package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class DefaultProductService implements ProductService {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final int PRICE_SCALE = 2;

    private ProductRepository repository = new ProductRepository();
    private ProductValidationService validationService = new ProductValidationService();

    @Override
    public Product findProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Long create(Product product) {
        validationService.validate(product);

        repository.insert(product);
        return product.getId();
    }

    @Override
    public BigDecimal calculateActualPrice(BigDecimal price, BigDecimal discount) {
        BigDecimal discountValue = price.divide(ONE_HUNDRED, PRICE_SCALE, HALF_UP).multiply(discount);
        return price.subtract(discountValue);
    }
}
