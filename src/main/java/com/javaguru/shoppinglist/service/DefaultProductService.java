package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.RoundingMode.HALF_UP;

@Service
public class DefaultProductService implements ProductService {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final int PRICE_SCALE = 2;

    private final ProductRepository repository;
    private final ProductValidationService validationService;

    @Autowired
    public DefaultProductService(ProductRepository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        checkNotNullId(id);
        return repository.findById(id);
    }

    @Override
    public Long create(Product product) {
        validationService.validate(product);

        Long id = repository.insert(product);

        product.setId(id);
        return id;
    }

    @Override
    public BigDecimal calculateActualPrice(BigDecimal price, BigDecimal discount) {
        BigDecimal discountValue = price.divide(ONE_HUNDRED, PRICE_SCALE, HALF_UP).multiply(discount);
        return price.subtract(discountValue);
    }

    private void checkNotNullId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
    }


}
