package com.travelapp.api.shop.DTOs.products;

import org.openapitools.jackson.nullable.JsonNullable;

public class ProductsUpdateDTO {
    private JsonNullable<Long> productId = JsonNullable.undefined();
    private JsonNullable<String> name = JsonNullable.undefined();
    private JsonNullable<String> type = JsonNullable.undefined();
    private JsonNullable<Double> price = JsonNullable.undefined();
    private JsonNullable<String> image = JsonNullable.undefined();
    private JsonNullable<Long> stock = JsonNullable.undefined();

    public JsonNullable<Long> getProductId() {
        return productId;
    }
    public void setProductId(JsonNullable<Long> productId) {
        this.productId = productId;
    }

    public JsonNullable<String> getName() {
        return name;
    }
    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getType() {
        return type;
    }
    public void setType(JsonNullable<String> type) {
        this.type = type;
    }

    public JsonNullable<Double> getPrice() {
        return price;
    }
    public void setPrice(JsonNullable<Double> price) {
        this.price = price;
    }

    public JsonNullable<String> getImage() {
        return image;
    }
    public void setImage(JsonNullable<String> image) {
        this.image = image;
    }

    public JsonNullable<Long> getStock() {
        return stock;
    }
    public void setStock(JsonNullable<Long> stock) {
        this.stock = stock;
    }
}
