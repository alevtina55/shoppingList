package com.javaguru.shoppinglist.dto;

public class ShoppingCartItemDTO {
    private Integer countOfProducts;
    private Long productId;

    public ShoppingCartItemDTO() {
    }

    public ShoppingCartItemDTO(Integer countOfProducts, Long productId) {
        this.countOfProducts = countOfProducts;
        this.productId = productId;
    }

    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(Integer countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
