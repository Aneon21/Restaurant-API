package com.restaurant.controllers;

import com.restaurant.mappers.requests.MenuItemRequest;
import com.restaurant.mappers.responses.MenuItemResponse;
import com.restaurant.services.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemService service;

    @PostMapping
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemRequest body){
        MenuItemResponse response = service.saveMenuItem(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems(){
        List<MenuItemResponse> menuList = service.getAllMenuItems();
        return ResponseEntity.status(HttpStatus.OK).body(menuList);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<MenuItemResponse> getMenuItemByID(@PathVariable(name = "id") int itemID){
        MenuItemResponse response = service.getMenuItemID(itemID);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable(name = "id") int itemID, @RequestBody MenuItemRequest body){
        MenuItemResponse response = service.updateMenuItem(itemID, body);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable(name = "id") int itemID){
        service.deleteMenuItem(itemID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
