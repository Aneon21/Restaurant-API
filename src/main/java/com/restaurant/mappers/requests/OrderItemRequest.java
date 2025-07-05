package com.restaurant.mappers.requests;

import com.restaurant.models.OrderItems;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequest {
    @NotNull
    @Positive
    private Integer menuItemId;

    @NotNull
    @Positive
    private Integer quantity;

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItedId) {
        this.menuItemId = menuItedId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
