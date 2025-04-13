package com.travelapp.api.AAshop.DTOs.products.other;

import org.openapitools.jackson.nullable.JsonNullable;

public class ProductsOtherUpdateDTO {
    private JsonNullable<Long> productId = JsonNullable.undefined();

    public JsonNullable<Long> getProductId() {
        return productId;
    }
    public void setProductId(JsonNullable<Long> productId) {
        this.productId = productId;
    }
}
