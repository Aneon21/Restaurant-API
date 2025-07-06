package com.restaurant.mappers.requests;
import com.restaurant.models.Orders;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderRequest {
    @NotBlank
    private String customerName;
    @NotBlank
    private String deliveryAddress;

    @Size(min = 1, message = "At least one item must be present in the order")
    @NotNull
    private List<OrderItemRequest> items;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public Orders toEntity(){
        Orders order = new Orders();

        order.setCustomerName(customerName);
        order.setDeliveryAddress(deliveryAddress);

        return order;
    }
}
