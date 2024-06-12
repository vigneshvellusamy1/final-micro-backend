
package com.sms.service;

import com.sms.model.Order;
import com.sms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public List<Order> getOrdersByShop(Long shopId) {
        return orderRepository.findByShopId(shopId);
    }

    public List<Order> getConfirmedOrdersByShop(Long shopId) {
        return orderRepository.findByShopIdAndStatus(shopId, "Confirmed");
    }

    public Order addOrder(Order order) {
        return orderRepository.addOrder(order);
    }

    public void approveOrder(Long orderId) {
        orderRepository.approveOrder(orderId);
    }

    public List<Order> findByDealerId(Long dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

	public void requestStock(Order order) {
		Order order1 = order;
		order1.setStatus("Pending");
		orderRepository.addOrder(order1);
	}
}
