package com.restaurant.models;

import com.restaurant.mappers.responses.OrderItemResponse;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {
    @EmbeddedId
    private OrderItemsId id = new OrderItemsId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuItemId")
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public OrderItemsId getId() {
        return id;
    }

    public void setId(OrderItemsId id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderItemResponse toResponse(){
        OrderItemResponse response = new OrderItemResponse();

        response.setCostPerItem(menuItem.getCost());
        response.setMenuItemId(menuItem.getId());
        response.setMenuItemName(menuItem.getName());
        response.setQuantity(quantity);
        response.setId(menuItem.getId());
        return response;
    }
}
