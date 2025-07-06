package com.restaurant.services;


import com.restaurant.mappers.requests.MenuItemRequest;
import com.restaurant.mappers.responses.MenuItemResponse;
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

    public MenuItemResponse saveMenuItem(MenuItemRequest request){
        MenuItem item = request.toEntity();
        item = repo.save(item);
        return item.toResponse();
    }

    public List<MenuItemResponse> getAllMenuItems(){
        return repo.findAll().stream().map(MenuItem::toResponse).toList();
    }


    public MenuItemResponse getMenuItemID(int itemID){
        MenuItem item = repo.findById(itemID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No menu item was found for the given id"));
        return item.toResponse();
    }

    public MenuItemResponse updateMenuItem(int itemID, MenuItemRequest body){
        MenuItem item = repo.findById(itemID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No menu item was found for the given id"));

        if(item != null){
            item.setCost(body.getCost());
            item.setName(body.getName());
            item.setDescription(body.getDescription());

            repo.save(item);
        }

        return item.toResponse();
    }

    public void deleteMenuItem(int itemID){
        MenuItem item = repo.findById(itemID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No menu item was found for the given id"));

        if(item != null){
            repo.delete(item);
        }
    }
}
