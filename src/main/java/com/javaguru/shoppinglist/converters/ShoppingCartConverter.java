package com.javaguru.shoppinglist.converters;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCartConverter {

    public ShoppingCart convert(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName(shoppingCartDTO.getName());
        shoppingCart.setId(shoppingCartDTO.getId());

        return shoppingCart;
    }

    public ShoppingCartDTO convert(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setName(shoppingCart.getName());
        shoppingCartDTO.setId(shoppingCart.getId());

        return shoppingCartDTO;
    }
}
