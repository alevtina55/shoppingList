package com.javaguru.shoppinglist.dto;

import java.util.Objects;

public class ShoppingCartItemData {
    private Long productId;
    private Integer countOfProducts;

    public ShoppingCartItemData() {
    }

    public ShoppingCartItemData(Long productId, Integer countOfProducts) {
        this.productId = productId;
        this.countOfProducts = countOfProducts;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(Integer countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItemData that = (ShoppingCartItemData) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(countOfProducts, that.countOfProducts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, countOfProducts);
    }

    @Override
    public String toString() {
        return "ShoppingCartItemData{" +
                "productId=" + productId +
                ", countOfProducts=" + countOfProducts +
                '}';
    }
}
