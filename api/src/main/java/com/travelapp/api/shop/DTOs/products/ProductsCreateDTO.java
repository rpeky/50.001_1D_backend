package com.travelapp.api.shop.DTOs.products;

public class ProductsCreateDTO {

    private String type;
    private String name;
    private Double price;
    private String image;
    private Long stock;


    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Long getStock() { return stock; }
    public void setStock(Long stock) { this.stock = stock; }
}

