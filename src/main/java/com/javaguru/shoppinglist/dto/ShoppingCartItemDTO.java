package com.javaguru.shoppinglist.dto;

import java.util.Objects;

public class ShoppingCartItemDTO {
    private Long id;
    private Integer countOfProducts;
    private ProductDTO productDTO;
    private ShoppingCartDTO shoppingCartDTO;

    public ShoppingCartItemDTO() {
    }

    public ShoppingCartItemDTO(Long id, Integer countOfProducts, ProductDTO productDTO,
                               ShoppingCartDTO shoppingCartDTO) {
        this.id = id;
        this.countOfProducts = countOfProducts;
        this.productDTO = productDTO;
        this.shoppingCartDTO = shoppingCartDTO;
    }

    public ShoppingCartItemDTO(Integer countOfProducts, ProductDTO productDTO) {
        this.countOfProducts = countOfProducts;
        this.productDTO = productDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(Integer countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ShoppingCartDTO getShoppingCartDTO() {
        return shoppingCartDTO;
    }

    public void setShoppingCartDTO(ShoppingCartDTO shoppingCartDTO) {
        this.shoppingCartDTO = shoppingCartDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItemDTO that = (ShoppingCartItemDTO) o;
        return Objects.equals(countOfProducts, that.countOfProducts) &&
                Objects.equals(productDTO, that.productDTO) &&
                Objects.equals(shoppingCartDTO, that.shoppingCartDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(countOfProducts, productDTO, shoppingCartDTO);
    }

    @Override
    public String toString() {
        return "ShoppingCartItemDTO{" +
                "countOfProducts=" + countOfProducts +
                ", productDTO=" + productDTO +
                ", shoppingCartDTO=" + shoppingCartDTO +
                '}';
    }
}
