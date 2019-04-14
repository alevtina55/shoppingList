package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.configuration.AppConfiguration;
import com.javaguru.shoppinglist.domain.Product;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = {HibernateProductRepository.class, AppConfiguration.class})
public class ProductRepositoryTestIT {
    @Autowired
    private ProductRepository victim;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void shouldInsertProduct() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        Long result = victim.insert(product);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindById() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        Long id = victim.insert(product);
        Optional<Product> result = victim.findById(id);
        assertThat(result).hasValue(expectedProduct(id));
    }

    @Test
    public void shouldExistsByName() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        victim.insert(product);

        boolean result = victim.existsByName("name");
        assertThat(result).isTrue();
    }

    @Test
    public void shouldNotExistsByName() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        victim.insert(product);

        boolean result = victim.existsByName("name1");
        assertThat(result).isFalse();
    }

    @Test
    public void shouldFindByName() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        victim.insert(product);
        Optional<Product> result = victim.findByName("name");
        assertThat(result).hasValue(product);
    }

    @Test
    public void shouldNotFindByName() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        victim.insert(product);
        Optional<Product> result = victim.findByName("name1");
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindAllProducts() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);
        Long productId = victim.insert(product);

        Product product1 = new Product();
        product1.setName("name1");
        product1.setPrice(BigDecimal.TEN);
        Long product1Id = victim.insert(product1);

        List<Product> result = victim.findAll();
        assertThat(result).contains(expectedProduct(productId), expectedProduct(product1Id, "name1"));
    }

    @Test
    public void shouldDeleteProduct() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);
        Long id = victim.insert(product);

        sessionFactory.getCurrentSession().beginTransaction();

        victim.delete(product);

        assertThat(victim.findById(id)).isEmpty();
    }


    private Product expectedProduct(Long id) {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);
        product.setId(id);

        return product;
    }


    private Product expectedProduct(Long id, String name) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(BigDecimal.TEN);
        product.setId(id);

        return product;
    }
}