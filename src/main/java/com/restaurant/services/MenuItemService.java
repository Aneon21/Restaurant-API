package com.restaurant.services;


import com.restaurant.models.MenuItem;
import com.restaurant.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    MenuItemRepository repo;

    public MenuItem saveMenuItem(MenuItem item){
        return repo.save(item);
    }

    public List<MenuItem> getAllMenuItems(){
        return repo.findAll();
    }

    public MenuItem getMenuItemByID(int itemID){
        return repo.findById((long)itemID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No menu item was found for the given id"));
    }

    public MenuItem updateMenuItem(int itemID, MenuItem body){
        MenuItem item = getMenuItemByID(itemID);

        if(item != null){
            item.setCost(body.getCost());
            item.setName(body.getName());
            item.setDescription(body.getDescription());

            repo.save(item);
        }

        return item;
    }

    public void deleteMenuItem(int itemID){
        MenuItem item = getMenuItemByID(itemID);

        if(item != null){
            repo.delete(item);
        }
    }
}
