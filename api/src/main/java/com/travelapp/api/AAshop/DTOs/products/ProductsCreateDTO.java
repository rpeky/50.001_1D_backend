package com.travelapp.api.AAshop.DTOs.products;

public class ProductsCreateDTO {
    private String type;
    private String name;
    private Long stock;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getStock() { return stock; }
    public void setStock(Long stock) { this.stock = stock; }
}

