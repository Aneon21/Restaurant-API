package com.restaurant.controllers;

import com.restaurant.mappers.requests.OrderRequest;
import com.restaurant.mappers.responses.OrderResponse;
import com.restaurant.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController

@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest body){
        if(body.getStatus() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Additional field 'status' should not be a part of the request body");
        }
        OrderResponse response = service.saveOrder(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        List<OrderResponse> response = service.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable(name = "id") int orderId){
        OrderResponse response = service.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrderById(@PathVariable(name = "id") int orderId, @Valid @RequestBody OrderRequest body){
        if(body.getStatus() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attribute 'status' cannot be empty or missing");
        }
        OrderResponse response = service.updateOrderById(orderId, body);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable(name = "id") int orderId){
        service.deleteOrderById(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
