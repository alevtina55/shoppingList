package com.javaguru.shoppinglist.controllers;

import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.dto.ShoppingCartItemDTO;
import com.javaguru.shoppinglist.dto.ShoppingCartItemData;
import com.javaguru.shoppinglist.service.ShoppingCartItemService;
import com.javaguru.shoppinglist.service.ShoppingCartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<Void> create(@Validated({ShoppingCartDTO.Create.class})
                                       @RequestBody ShoppingCartDTO shoppingCartDTO,
                                       UriComponentsBuilder builder) {
        Long id = shoppingCartService.saveShoppingCart(shoppingCartDTO);

        return ResponseEntity.created(builder.path("/shoppingCarts/{id}").buildAndExpand(id)
                .toUri()).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingCartById(@PathVariable("id") Long id) {
        shoppingCartItemService.deleteShoppingCartItems(id);
        shoppingCartService.deleteShoppingCart(id);
    }

    @PutMapping(value = "/{id}/items")
    public Long addShoppingCartItem(@PathVariable("id") Long id,
                                    @RequestBody ShoppingCartItemData cartItemData) {
        return shoppingCartItemService.createShoppingCartItem(id, cartItemData.getProductId(),
                cartItemData.getCountOfProducts());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartDTO findShoppingCartById(@PathVariable("id") Long id) {
        return shoppingCartService.findShoppingCartById(id);
    }

    @GetMapping("/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingCartItemDTO> findShoppingCartItems(@PathVariable("id") Long id) {

        return shoppingCartItemService.findItemsByCartId(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException ex) {

    }
}
