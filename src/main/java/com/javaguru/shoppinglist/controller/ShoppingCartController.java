package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.dto.ShoppingCartItemDTO;
import com.javaguru.shoppinglist.service.ShoppingCartItemService;
import com.javaguru.shoppinglist.service.ShoppingCartService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> create(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName(shoppingCartDTO.getName());
        shoppingCartService.saveShoppingCart(shoppingCart);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteShoppingCartById(@PathVariable("id") Long id) {
        shoppingCartItemService.deleteShoppingCartItems(id);
        shoppingCartService.deleteShoppingCart(id);

        return "Shopping cart was successfully deleted";
    }

    @PutMapping("/update/{id}")
    public Long addShoppingCartItem(@PathVariable("id") Long id,
                                    @RequestBody ShoppingCartItemDTO shoppingCartItemData) {
        return shoppingCartItemService.createShoppingCartItem(
                id,
                shoppingCartItemData.getProductId(),
                shoppingCartItemData.getCountOfProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ShoppingCartItem>> findShoppingCart(@PathVariable("id") Long id) {
        List<ShoppingCartItem> items = shoppingCartItemService.findItemsByCartId(id);

        return ResponseEntity.ok(items);
    }
}
