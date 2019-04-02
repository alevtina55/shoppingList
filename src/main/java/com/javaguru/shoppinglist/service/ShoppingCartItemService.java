package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.repository.HibernateShoppingCartItemRepository;
import com.javaguru.shoppinglist.service.validation.ItemValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShoppingCartItemService {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final HibernateShoppingCartItemRepository shoppingCartItemRepository;
    private final ItemValidationService itemValidationService;

    @Autowired
    public ShoppingCartItemService(ProductService productService,
                                   ShoppingCartService shoppingCartService,
                                   HibernateShoppingCartItemRepository shoppingCartItemRepository,
                                   ItemValidationService itemValidationService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.itemValidationService = itemValidationService;
    }

    public Long createShoppingCartItem(Long shoppingCartId, Long productId, Integer count) {
        Product product = productService.findProductById(productId);
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(shoppingCartId);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setCountOfProducts(count);

        itemValidationService.validate(shoppingCartItem);

        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    public List<ShoppingCartItem> findItemsByCartId(Long id) {
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(id);

        return shoppingCartItemRepository.getItemsInShoppingCart(shoppingCart);
    }

    public BigDecimal calcItemsPrice(Long shoppingCartId) {
        List<ShoppingCartItem> items = findItemsByCartId(shoppingCartId);

        return items.stream()
                .map(item -> {
                    BigDecimal productPrice = item.getProduct().getActualPrice();
                    return productPrice.multiply(new BigDecimal(item.getCountOfProducts()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void deleteItem(Long id) {
        ShoppingCartItem shoppingCartItem = findItemById(id);
        if (shoppingCartItem != null) {
            shoppingCartItemRepository.delete(shoppingCartItem);
        }
    }

    public void deleteShoppingCartItems(Long shoppingCartId) {
        List<ShoppingCartItem> items = findItemsByCartId(shoppingCartId);
        shoppingCartItemRepository.deleteItems(items);
    }

    private ShoppingCartItem findItemById(Long id) {
        return shoppingCartItemRepository.findById(id).orElseThrow((() ->
                new NoSuchElementException("There is no shopping cart item with id: " + id)));
    }
}
