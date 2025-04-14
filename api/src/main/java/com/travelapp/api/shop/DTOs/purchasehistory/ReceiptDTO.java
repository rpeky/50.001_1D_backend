package com.travelapp.api.shop.DTOs.purchasehistory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDTO {
    private UsersOtherReadDTO user;
    private List<PurchaseHistoryReadListDTO> items;
    private String message;

    public UsersOtherReadDTO getUser() { return user; }
    public void setUser(UsersOtherReadDTO user) { this.user = user; }

    public List<PurchaseHistoryReadListDTO> getItems() { return items; }
    public void setItems(List<PurchaseHistoryReadListDTO> items) { this.items = items; }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

