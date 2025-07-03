package com.restaurant.services;


import com.restaurant.models.MenuItem;
import com.restaurant.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {

    @Autowired
    MenuItemRepository repo;

    public MenuItem saveMenuItem(MenuItem item){
        return repo.save(item);
    }
}
