package com.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.mappers.requests.MenuItemRequest;
import com.restaurant.mappers.responses.MenuItemResponse;
import com.restaurant.services.MenuItemService;
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
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MenuController.class)
public class MenuControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MenuItemService menuItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createMenuItemTest() throws Exception{
        MenuItemRequest request = new MenuItemRequest();
        request.setName("Apple Pie");
        request.setDescription("Traditional dessert with spiced apples in a flaky pastry crust");
        request.setCost(200);

        MenuItemResponse response = new MenuItemResponse();
        response.setId(1);
        response.setName("Apple Pie");
        response.setDescription("Traditional dessert with spiced apples in a flaky pastry crust");
        response.setCost(200);

        when(menuItemService.saveMenuItem(any(MenuItemRequest.class))).thenReturn(response);

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Apple Pie"))
                .andExpect(jsonPath("$.description").value("Traditional dessert with spiced apples in a flaky pastry crust"))
                .andExpect(jsonPath("$.cost").value(200));
    }

    @Test
    public void updateMenuItemTest() throws Exception{
        int menuItemId = 1;
        MenuItemRequest request = new MenuItemRequest();
        request.setName("Apple Pie");
        request.setDescription("Traditional dessert with spiced apples in a flaky pastry crust");
        request.setCost(200);

        MenuItemResponse response = new MenuItemResponse();
        response.setId(menuItemId);
        response.setName("Apple Pie");
        response.setDescription("Traditional dessert with spiced apples in a flaky pastry crust");
        response.setCost(200);

        when(menuItemService.updateMenuItem(eq(menuItemId), any(MenuItemRequest.class))).thenReturn(response);

        mockMvc.perform(put("/menu/{id}", menuItemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(menuItemId))
                .andExpect(jsonPath("$.name").value("Apple Pie"))
                .andExpect(jsonPath("$.description").value("Traditional dessert with spiced apples in a flaky pastry crust"))
                .andExpect(jsonPath("$.cost").value(200));
    }

    @Test
    public void getMenuItemByIDTest() throws Exception{
        int menuItemId = 1;

        MenuItemResponse response = new MenuItemResponse();
        response.setId(menuItemId);
        response.setName("Apple Pie");
        response.setDescription("Traditional dessert with spiced apples in a flaky pastry crust");
        response.setCost(200);

        when(menuItemService.getMenuItemID(eq(menuItemId))).thenReturn(response);

        mockMvc.perform(get("/menu/{id}", menuItemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(menuItemId))
                .andExpect(jsonPath("$.name").value("Apple Pie"))
                .andExpect(jsonPath("$.description").value("Traditional dessert with spiced apples in a flaky pastry crust"))
                .andExpect(jsonPath("$.cost").value(200));
    }

    @Test
    public void deleteMenuItemById() throws Exception{
        int menuItemId = 1;

        doNothing().when(menuItemService).deleteMenuItem(menuItemId);

        mockMvc.perform(delete("/menu/{id}",menuItemId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllMenuItems() throws Exception {

        MenuItemResponse item1 = new MenuItemResponse();
        item1.setId(1);
        item1.setName("Pizza");
        item1.setDescription("Cheese burst");
        item1.setCost(250);

        MenuItemResponse item2 = new MenuItemResponse();
        item2.setId(2);
        item2.setName("Burger");
        item2.setDescription("Veg loaded");
        item2.setCost(150);

        List<MenuItemResponse> mockMenuList = List.of(item1, item2);

        when(menuItemService.getAllMenuItems()).thenReturn(mockMenuList);

        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[0].description").value("Cheese burst"))
                .andExpect(jsonPath("$[0].cost").value(250))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Burger"))
                .andExpect(jsonPath("$[1].description").value("Veg loaded"))
                .andExpect(jsonPath("$[1].cost").value(150));
    }
}
