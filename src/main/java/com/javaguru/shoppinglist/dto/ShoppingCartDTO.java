package com.javaguru.shoppinglist.dto;

public class ShoppingCartDTO {
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
}
