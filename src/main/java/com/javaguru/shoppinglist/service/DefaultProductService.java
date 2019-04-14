package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converters.ProductConverter;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;

@Service
public class DefaultProductService implements ProductService {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final int PRICE_SCALE = 2;

    private final ProductRepository repository;
    private final ProductValidationService validationService;
    private final ProductConverter productConverter;

    @Autowired
    public DefaultProductService(ProductRepository repository,
                                 ProductValidationService validationService, ProductConverter productConverter) {
        this.repository = repository;
        this.validationService = validationService;
        this.productConverter = productConverter;
    }

    @Override
    public ProductDTO findProductById(Long id) {
        checkNotNullId(id);
        return repository.findById(id).map(productConverter::convert)
                .orElseThrow(() ->
                        new NoSuchElementException("There is no product with id: " + id));
    }

    @Override
    public Long create(ProductDTO productDTO) {
        BigDecimal actualPrice = calculateActualPrice(productDTO.getPrice(),
                productDTO.getDiscount());
        productDTO.setActualPrice(actualPrice);
        validationService.validate(productDTO);

        Product product = productConverter.convert(productDTO);

        return repository.insert(product);
    }

    @Override
    public void deleteProductById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public BigDecimal calculateActualPrice(BigDecimal price, BigDecimal discount) {
        BigDecimal discountValue = price.divide(ONE_HUNDRED, PRICE_SCALE, HALF_UP)
                .multiply(discount);
        return price.subtract(discountValue);
    }

    @Override
    public void updateDescription(Long id, String newDescription) {
        Product product = productConverter.convert(findProductById(id));
        product.setDescription(newDescription);
        repository.update(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream().map(productConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductByName(String name) {
        return productConverter.convert(repository.findByName(name).orElseThrow(() ->
                new NoSuchElementException("There is no product with name: " + name)));
    }


    private void checkNotNullId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
    }
}
