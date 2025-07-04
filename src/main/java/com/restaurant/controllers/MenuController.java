package com.restaurant.controllers;

import com.restaurant.models.MenuItem;
import com.restaurant.services.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemService service;

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@Valid @RequestBody MenuItem item){
        if(item.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'id' should not be passed in the body");
        }
        MenuItem result = service.saveMenuItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems(){
        List<MenuItem> menuList = service.getAllMenuItems();

        return ResponseEntity.status(HttpStatus.OK).body(menuList);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<MenuItem> getMenuItemByID(@PathVariable(name = "id") int itemID){
        MenuItem item = service.getMenuItemByID(itemID);

        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable(name = "id") int itemID, @RequestBody MenuItem body){
        if(body.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Additional fields are present in request body");
        }
        MenuItem item = service.updateMenuItem(itemID, body);

        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable(name = "id") int itemID){
        service.deleteMenuItem(itemID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
