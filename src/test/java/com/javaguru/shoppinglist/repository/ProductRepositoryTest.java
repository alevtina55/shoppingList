package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductRepositoryTest {
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    private static final long PRODUCT_ID = 0L;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductRepository victim = new ProductRepository();

    private Product product = product();

    @Test
    public void shouldInsertSuccessfully() {
        Long result = victim.insert(product);

        assertEquals(expectedProduct().getId(), result);
    }

    @Test
    public void shouldFindByID() {
        victim.insert(product);

        Product resultProduct = victim.findById(PRODUCT_ID);

        assertEquals(expectedProduct(), resultProduct);
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