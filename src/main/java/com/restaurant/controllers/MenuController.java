package com.restaurant.controllers;

import com.restaurant.models.MenuItem;
import com.restaurant.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemService service;

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item){
        System.out.println(item);
        MenuItem result = service.saveMenuItem(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
