package com.restaurant.services;

import com.restaurant.mappers.requests.OrderItemRequest;
import com.restaurant.mappers.requests.OrderRequest;
import com.restaurant.mappers.responses.OrderItemResponse;
import com.restaurant.mappers.responses.OrderResponse;
import com.restaurant.models.MenuItem;
import com.restaurant.models.OrderItems;
import com.restaurant.models.Orders;
import com.restaurant.repositories.OrdersRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository rep;

    @Autowired
    private MenuItemService menuItemService;

    @Transactional
    public OrderResponse saveOrder(OrderRequest order){
        int total = 0;
        Orders entity = order.toEntity();

        List<OrderItems> orderItems = new ArrayList<>();

        for(OrderItemRequest orderItemRequest : order.getItems()){
            MenuItem item = menuItemService.getMenuItemByID(orderItemRequest.getMenuItemId());


            int cost = item.getCost() * orderItemRequest.getQuantity();

            total += cost;

            OrderItems orderItem = new OrderItems();
            orderItem.setMenuItem(item);
            orderItem.setOrder(entity);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItems.add(orderItem);
        }

        entity.setCost(total);
        entity.setOrderItems(orderItems);

        Orders result = rep.save(entity);

        return result.toResponse();
    }

    public List<OrderResponse> getAllOrders(){
        List<Orders> orders = rep.findAll();

        return orders.stream().map(Orders::toResponse).toList();
    }

    public OrderResponse getOrderById(int orderId){
        Orders order =  rep.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order was found for the given id"));

        return order.toResponse();
    }

    public OrderResponse updateOrderById(int orderId, OrderRequest request){
        Orders order = rep.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order was found for the given id"));
        int total = 0;
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setCustomerName(request.getCustomerName());

        List<OrderItems> items = new ArrayList<>();
        order.getOrderItems().clear();
        rep.flush();
        for(OrderItemRequest req : request.getItems()){
            MenuItem menuItem = menuItemService.getMenuItemByID(req.getMenuItemId());
            int cost = menuItem.getCost() * req.getQuantity();

            total += cost;

            OrderItems item = new OrderItems();
            item.setMenuItem(menuItem);
            item.setQuantity(req.getQuantity());
            item.setOrder(order);

            order.getOrderItems().add(item);
        }
        order.setCost(total);

        order = rep.save(order);

        return order.toResponse();
    }

    public void deleteOrderById(int orderId){
        Orders order = rep.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order was found for the given id"));

        rep.delete(order);
    }
}
