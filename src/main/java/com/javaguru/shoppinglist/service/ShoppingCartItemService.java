package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converters.ShoppingCartConverter;
import com.javaguru.shoppinglist.converters.ShoppingCartItemConverter;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.dto.ShoppingCartItemDTO;
import com.javaguru.shoppinglist.repository.HibernateShoppingCartItemRepository;
import com.javaguru.shoppinglist.service.validation.ItemValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class ShoppingCartItemService {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final HibernateShoppingCartItemRepository cartItemRepository;
    private final ItemValidationService itemValidationService;
    private final ShoppingCartItemConverter cartItemConverter;
    private final ShoppingCartConverter cartConverter;

    @Autowired
    public ShoppingCartItemService(ProductService productService,
                                   ShoppingCartService shoppingCartService,
                                   HibernateShoppingCartItemRepository cartItemRepository,
                                   ItemValidationService itemValidationService,
                                   ShoppingCartItemConverter cartItemConverter,
                                   ShoppingCartConverter cartConverter) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.cartItemRepository = cartItemRepository;
        this.itemValidationService = itemValidationService;
        this.cartItemConverter = cartItemConverter;
        this.cartConverter = cartConverter;
    }

    public Long createShoppingCartItem(Long shoppingCartId, Long productId, Integer count) {
        ProductDTO productDTO = productService.findProductById(productId);
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.findShoppingCartById(shoppingCartId);

        ShoppingCartItemDTO shoppingCartItemDTO = new ShoppingCartItemDTO(null, count, productDTO,
                shoppingCartDTO);

        itemValidationService.validate(shoppingCartItemDTO);

        return cartItemRepository.save(cartItemConverter.convert(shoppingCartItemDTO));
    }

    public List<ShoppingCartItemDTO> findItemsByCartId(Long id) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.findShoppingCartById(id);
        ShoppingCart shoppingCart = cartConverter.convert(shoppingCartDTO);

        List<ShoppingCartItem> cartItemList = cartItemRepository
                .getItemsInShoppingCart(shoppingCart);

        return cartItemList.stream()
                .map(cartItemConverter::convert)
                .collect(Collectors.toList());
    }

    public BigDecimal calcItemsPrice(Long shoppingCartId) {
        List<ShoppingCartItemDTO> items = findItemsByCartId(shoppingCartId);

        return items.stream()
                .map(item -> {
                    BigDecimal productPrice = item.getProductDTO().getActualPrice();
                    return productPrice.multiply(new BigDecimal(item.getCountOfProducts()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void deleteItem(Long id) {
        ShoppingCartItem shoppingCartItem = findItemById(id);
        if (shoppingCartItem != null) {
            cartItemRepository.delete(shoppingCartItem);
        }
    }

    @Transactional
    public void deleteShoppingCartItems(Long shoppingCartId) {
        List<ShoppingCartItemDTO> itemsDTO = findItemsByCartId(shoppingCartId);
        List<ShoppingCartItem> items = itemsDTO.stream()
                .map(cartItemConverter::convert)
                .collect(Collectors.toList());
        if (!items.isEmpty()) {
            cartItemRepository.deleteItems(items);
        }
    }

    private ShoppingCartItem findItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow((() ->
                new NoSuchElementException("There is no shopping cart item with id: " + id)));
    }
}
