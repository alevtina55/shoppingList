package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InMemoryProductRepositoryTest {
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    private static final long PRODUCT_ID = 0L;

    private InMemoryProductRepository victim = new InMemoryProductRepository();

    private Product product = product();

    @Test
    public void shouldInsertSuccessfully() {
        Long result = victim.insert(product);

        assertThat(result).isEqualTo(expectedProduct().getId());
    }

    @Test
    public void shouldFindByID() {
        victim.insert(product);

        Optional<Product> resultProduct = victim.findById(PRODUCT_ID);

        assertThat(resultProduct).isNotEmpty();
        assertThat(resultProduct).hasValue(expectedProduct());
    }

    @Test
    public void shouldReturnTrue() {
        victim.insert(product);

        boolean result = victim.existsByName(PRODUCT_NAME);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse() {
        victim.insert(product);

        boolean result = victim.existsByName("NAME");

        assertFalse(result);
    }

    private Product expectedProduct() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setId(PRODUCT_ID);
        return product;
    }

    private Product product() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setId(PRODUCT_ID);
        return product;
    }
}