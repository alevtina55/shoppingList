package com.javaguru.shoppinglist.dto;

import java.util.Objects;

import javax.validation.constraints.Null;

public class ShoppingCartDTO {
    @Null(groups = Create.class)
    private Long id;
    private String name;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartDTO that = (ShoppingCartDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public interface Create {
    }
}
