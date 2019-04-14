package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.configuration.AppConfiguration;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.domain.ShoppingCartItem;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = {HibernateShoppingCartItemRepository.class, AppConfiguration.class,
        HibernateShoppingCartRepository.class, HibernateProductRepository.class})
public class HibernateShoppingCartItemRepositoryTestIT {
    @Autowired
    private HibernateShoppingCartItemRepository victim;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    HibernateShoppingCartRepository shoppingCartRepository;

    @Test
    public void shouldSaveCartItem() {
        ShoppingCart shoppingCart = shoppingCart();
        shoppingCartRepository.save(shoppingCart);

        Product product = product();
        productRepository.insert(product);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setCountOfProducts(10);
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setProduct(product);

        Long result = victim.save(shoppingCartItem);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindShoppingCartById() {
        ShoppingCart shoppingCart = shoppingCart();
        shoppingCartRepository.save(shoppingCart);

        Product product = product();
        productRepository.insert(product);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setCountOfProducts(10);
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setProduct(product);

        Long id = victim.save(shoppingCartItem);

        Optional<ShoppingCartItem> result = victim.findById(id);

        assertThat(result).hasValue(expectedCartItem(id, product, shoppingCart));
    }

    private ShoppingCartItem expectedCartItem(Long id, Product product, ShoppingCart shoppingCart) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setCountOfProducts(10);
        shoppingCartItem.setId(id);

        return shoppingCartItem;
    }

    private Product product() {
        Product product = new Product();
        product.setName("name");
        product.setPrice(BigDecimal.TEN);

        return product;
    }

    private ShoppingCart shoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("name");

        return shoppingCart;
    }
}