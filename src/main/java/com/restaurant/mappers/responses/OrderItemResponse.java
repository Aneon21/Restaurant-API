package com.restaurant.mappers.responses;

public class OrderItemResponse {
    private Integer id;
    private Integer menuItemId;
    private String menuItemName;
    private Integer quantity;
    private Integer costPerItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCostPerItem() {
        return costPerItem;
    }

    public void setCostPerItem(Integer costPerItem) {
        this.costPerItem = costPerItem;
    }
}
