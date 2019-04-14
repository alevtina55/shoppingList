package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converters.ShoppingCartConverter;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.repository.HibernateShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

@Service
public class ShoppingCartService {
    private final HibernateShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartConverter shoppingCartConverter;

    @Autowired
    public ShoppingCartService(HibernateShoppingCartRepository shoppingCartRepository,
                               ShoppingCartConverter shoppingCartConverter) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartConverter = shoppingCartConverter;
    }

    public Long saveShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartConverter.convert(shoppingCartDTO);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCartDTO findShoppingCartById(Long id) {
        return shoppingCartRepository.findShoppingCartById(id).map(shoppingCartConverter::convert)
                .orElseThrow(() ->
                        new NoSuchElementException("There is no shopping cart with id: " + id));
    }

    @Transactional
    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.findShoppingCartById(id)
                .ifPresent(shoppingCartRepository::delete);
    }
}
