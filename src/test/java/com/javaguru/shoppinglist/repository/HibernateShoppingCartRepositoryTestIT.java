package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.configuration.AppConfiguration;
import com.javaguru.shoppinglist.domain.ShoppingCart;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = {HibernateShoppingCartRepository.class, AppConfiguration.class})
public class HibernateShoppingCartRepositoryTestIT {

    @Autowired
    private HibernateShoppingCartRepository victim;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void shouldSaveShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("name");

        Long result = victim.save(shoppingCart);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindShoppingCartById() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("name");

        Long id = victim.save(shoppingCart);

        Optional<ShoppingCart> result = victim.findShoppingCartById(id);

        assertThat(result).hasValue(expectedShoppingCart(id));
    }

    @Test
    public void shouldDeleteShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("name");

        Long id = victim.save(shoppingCart);

        sessionFactory.getCurrentSession().beginTransaction();
        victim.delete(shoppingCart);

        assertThat(victim.findShoppingCartById(id)).isEmpty();

    }


    public ShoppingCart expectedShoppingCart(Long id) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("name");
        shoppingCart.setId(id);

        return shoppingCart;
    }
}