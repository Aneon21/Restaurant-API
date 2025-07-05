package com.restaurant.models;

import com.restaurant.mappers.responses.MenuItemResponse;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "item_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orders = new ArrayList<>();

    public List<OrderItems> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItems> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public MenuItemResponse toResponse(){
        MenuItemResponse response = new MenuItemResponse();

        response.setCost(cost);
        response.setName(name);
        response.setDescription(description);
        response.setId(id);

        return response;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
