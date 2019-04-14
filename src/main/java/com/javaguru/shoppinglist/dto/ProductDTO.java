package com.javaguru.shoppinglist.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Null;


public class ProductDTO {
    @Null(groups = {Create.class})
    private Long id;
    @NotBlank(groups = {Create.class})
    private String name;
    private BigDecimal price;
    private String category;
    private BigDecimal discount;
    private String description;
    @Null(groups = {Create.class})
    private BigDecimal actualPrice;


    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, BigDecimal price, String category, BigDecimal discount,
                      String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(category, that.category) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(actualPrice, that.actualPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, category, discount, description, actualPrice);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", actualPrice=" + actualPrice +
                '}';
    }

    public interface Create {

    }
}
