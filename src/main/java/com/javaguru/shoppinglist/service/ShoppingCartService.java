package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.HibernateShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShoppingCartService {
    private final HibernateShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(HibernateShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Long saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart findShoppingCartById(Long id) {
        return shoppingCartRepository.findShoppingCartById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("There is no shopping cart with id: " + id));
    }

    public void deleteShoppingCart(Long id) {
        ShoppingCart shoppingCart = findShoppingCartById(id);
        shoppingCartRepository.delete(shoppingCart);
    }
}
