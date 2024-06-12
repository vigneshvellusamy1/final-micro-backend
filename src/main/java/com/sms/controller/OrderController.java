
package com.sms.controller;

import com.sms.model.Order;
import com.sms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/approve/{id}")
    public void approveOrder(@PathVariable Long id) {
        orderService.approveOrder(id);
    }

    @GetMapping("/shop/{shopId}")
    public List<Order> getOrdersByShop(@PathVariable Long shopId) {
        return orderService.getOrdersByShop(shopId);
    }

    @GetMapping("/shop/{shopId}/confirmed")
    public List<Order> getConfirmedOrdersByShop(@PathVariable Long shopId) {
        return orderService.getConfirmedOrdersByShop(shopId);
    }
    
    @GetMapping("/getOrderByDealerId/{id}")
    public List<Order> getorder(@PathVariable("id")long id){
    	return orderService.findByDealerId(id);
    }
}
