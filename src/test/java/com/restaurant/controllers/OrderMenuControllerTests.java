package com.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.mappers.requests.OrderItemRequest;
import com.restaurant.mappers.requests.OrderRequest;
import com.restaurant.mappers.responses.OrderItemResponse;
import com.restaurant.mappers.responses.OrderResponse;
import com.restaurant.services.OrderService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Import(OrderController.class)
public class OrderMenuControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    OrderService orderService;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testCreateOrder_success() throws Exception {
        OrderRequest request = new OrderRequest();
        request.setCustomerName("John Doe");
        request.setDeliveryAddress("123 Main Street, Springfield");

        OrderItemRequest item = new OrderItemRequest();
        item.setMenuItemId(3);
        item.setQuantity(1);
        request.setItems(List.of(item));

        OrderResponse mockResponse = new OrderResponse();
        mockResponse.setId(4);
        mockResponse.setCustomerName("John Doe");
        mockResponse.setDeliveryAddress("123 Main Street, Springfield");
        mockResponse.setStatus("PENDING");
        mockResponse.setCost(300);

        OrderItemResponse itemResponse = new OrderItemResponse();
        itemResponse.setId(3);
        itemResponse.setMenuItemId(3);
        itemResponse.setMenuItemName("Buffalo Wings");
        itemResponse.setQuantity(1);
        itemResponse.setCostPerItem(300);

        mockResponse.setItems(List.of(itemResponse));

        when(orderService.saveOrder(any(OrderRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.deliveryAddress").value("123 Main Street, Springfield"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.cost").value(300))
                .andExpect(jsonPath("$.items[0].id").value(3))
                .andExpect(jsonPath("$.items[0].menuItemId").value(3))
                .andExpect(jsonPath("$.items[0].menuItemName").value("Buffalo Wings"))
                .andExpect(jsonPath("$.items[0].quantity").value(1))
                .andExpect(jsonPath("$.items[0].costPerItem").value(300));
    }

    @Test
    public void getAllOrderTest() throws Exception{
        OrderResponse response = new OrderResponse();
        response.setId(1);
        response.setCustomerName("John Doe");
        response.setDeliveryAddress("123 Main Street, Springfield");
        response.setStatus("COMPLETED");
        response.setCost(800);

        OrderItemResponse item = new OrderItemResponse();

        item.setId(1);
        item.setMenuItemId(1);
        item.setMenuItemName("Margherita Pizza");
        item.setQuantity(2);
        item.setCostPerItem(400);

        response.setItems(List.of(item));

        when(orderService.getAllOrders()).thenReturn(List.of(response));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$[0].deliveryAddress").value("123 Main Street, Springfield"))
                .andExpect(jsonPath("$[0].status").value("COMPLETED"))
                .andExpect(jsonPath("$[0].items[0].id").value(1))
                .andExpect(jsonPath("$[0].items[0].menuItemId").value(1))
                .andExpect(jsonPath("$[0].items[0].menuItemName").value("Margherita Pizza"))
                .andExpect(jsonPath("$[0].items[0].quantity").value(2))
                .andExpect(jsonPath("$[0].items[0].costPerItem").value(400));
    }

    @Test
    public void getOrderByIdTest() throws Exception{
        OrderResponse response = new OrderResponse();
        int orderId = 1;
        response.setId(orderId);
        response.setCustomerName("John Doe");
        response.setDeliveryAddress("123 Main Street, Springfield");
        response.setStatus("COMPLETED");
        response.setCost(800);

        OrderItemResponse item = new OrderItemResponse();

        item.setId(1);
        item.setMenuItemId(1);
        item.setMenuItemName("Margherita Pizza");
        item.setQuantity(2);
        item.setCostPerItem(400);
        response.setItems(List.of(item));

        when(orderService.getOrderById(orderId)).thenReturn(response);

        mockMvc.perform(get("/orders/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.deliveryAddress").value("123 Main Street, Springfield"))
                .andExpect(jsonPath("$.status").value("COMPLETED"))
                .andExpect(jsonPath("$.items[0].id").value(1))
                .andExpect(jsonPath("$.items[0].menuItemId").value(1))
                .andExpect(jsonPath("$.items[0].menuItemName").value("Margherita Pizza"))
                .andExpect(jsonPath("$.items[0].quantity").value(2))
                .andExpect(jsonPath("$.items[0].costPerItem").value(400));
    }


    @Test
    public void testUpdateOrder_success() throws Exception {
        int orderId = 4;
        String requestBody = """
        {
            "customerName": "John Doe Updated",
            "deliveryAddress": "789 Oak Avenue, Capital City",
            "status": "COMPLETED",
            "items": [
                {
                    "menuItemId": 3,
                    "quantity": 2
                }
            ]
        }
    """;

        OrderResponse updatedResponse = new OrderResponse();
        updatedResponse.setId(orderId);
        updatedResponse.setCustomerName("John Doe Updated");
        updatedResponse.setDeliveryAddress("789 Oak Avenue, Capital City");
        updatedResponse.setStatus("COMPLETED");
        updatedResponse.setCost(600);

        OrderItemResponse updatedItem = new OrderItemResponse();
        updatedItem.setId(3);
        updatedItem.setMenuItemId(3);
        updatedItem.setMenuItemName("Buffalo Wings");
        updatedItem.setQuantity(2);
        updatedItem.setCostPerItem(300);

        updatedResponse.setItems(List.of(updatedItem));

        when(orderService.updateOrderById(eq(orderId), any(OrderRequest.class)))
                .thenReturn(updatedResponse);

        mockMvc.perform(put("/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.customerName").value("John Doe Updated"))
                .andExpect(jsonPath("$.deliveryAddress").value("789 Oak Avenue, Capital City"))
                .andExpect(jsonPath("$.status").value("COMPLETED"))
                .andExpect(jsonPath("$.cost").value(600))
                .andExpect(jsonPath("$.items[0].id").value(3))
                .andExpect(jsonPath("$.items[0].menuItemId").value(3))
                .andExpect(jsonPath("$.items[0].menuItemName").value("Buffalo Wings"))
                .andExpect(jsonPath("$.items[0].quantity").value(2))
                .andExpect(jsonPath("$.items[0].costPerItem").value(300));
    }

    @Test
    public void deleteOrderById() throws Exception{
        doNothing().when(orderService).deleteOrderById(eq(1));

        mockMvc.perform(delete("/orders/{id}", 1))
                .andExpect(status().isNoContent());

    }
}
