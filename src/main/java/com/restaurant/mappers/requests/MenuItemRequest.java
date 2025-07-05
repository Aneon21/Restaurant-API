package com.restaurant.mappers.requests;


import com.restaurant.models.MenuItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MenuItemRequest {

    @NotBlank(message = "Cannot have an item without any name")
    private String name;
    private String description;

    @NotNull(message = "Cannot have an item without any price")
    @Positive(message = "The cost of the item has to be postive")
    private Integer cost;


    public MenuItem toEntity(){
        MenuItem item = new MenuItem();
        item.setDescription(description);
        item.setName(name);
        item.setCost(cost);

        return item;
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

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
