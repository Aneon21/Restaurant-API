package com.restaurant.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemsId implements Serializable {
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "menu_item_id")
    private Integer menuItemId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuItemId);
    }
}
