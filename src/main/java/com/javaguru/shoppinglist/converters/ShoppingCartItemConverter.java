package com.javaguru.shoppinglist.converters;

import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.dto.ShoppingCartItemDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartItemConverter {
    private final ProductConverter productConverter;
    private final ShoppingCartConverter shoppingCartConverter;

    @Autowired
    public ShoppingCartItemConverter(ProductConverter productConverter,
                                     ShoppingCartConverter shoppingCartConverter) {
        this.productConverter = productConverter;
        this.shoppingCartConverter = shoppingCartConverter;
    }

    public ShoppingCartItem convert(ShoppingCartItemDTO cartItemDTO) {
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setShoppingCart(shoppingCartConverter.convert(cartItemDTO.getShoppingCartDTO()));
        cartItem.setProduct(productConverter.convert(cartItemDTO.getProductDTO()));
        cartItem.setCountOfProducts(cartItemDTO.getCountOfProducts());
        cartItem.setId(cartItemDTO.getId());

        return cartItem;
    }

    public ShoppingCartItemDTO convert(ShoppingCartItem cartItem) {
        ShoppingCartItemDTO cartItemDTO = new ShoppingCartItemDTO();

        cartItemDTO.setShoppingCartDTO(shoppingCartConverter.convert(cartItem.getShoppingCart()));
        cartItemDTO.setProductDTO(productConverter.convert(cartItem.getProduct()));
        cartItemDTO.setCountOfProducts(cartItem.getCountOfProducts());
        cartItemDTO.setId(cartItem.getId());

        return cartItemDTO;
    }
}
