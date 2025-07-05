package com.restaurant.models;

import com.restaurant.mappers.responses.OrderResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "status")
    private String status = "PENDING";

    @Column(name = "total_cost")
    private Integer cost;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @OneToMany(mappedBy = "order")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public OrderResponse toResponse(){
        OrderResponse response = new OrderResponse();

        response.setCost(cost);
        response.setCustomerName(customerName);
        response.setDeliveryAddress(deliveryAddress);
        response.setStatus(status);
        response.setItems(orderItems.stream().map(OrderItems::toResponse).toList());
        response.setId(id);
        return response;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status='" + status + '\'' +
                ", cost=" + cost +
                '}';
    }
}
