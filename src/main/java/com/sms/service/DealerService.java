package com.sms.service;

import com.sms.model.Dealer;
import com.sms.model.Order;
import com.sms.model.Product;
import com.sms.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {
    @Autowired
    private DealerRepository dealerRepository;

    public List<Dealer> getAllDealers() {
        return dealerRepository.getAllDealers();
    }

    public Dealer addDealer(Dealer dealer) {
    	dealer.setStatus("Pending");
        return dealerRepository.addDealer(dealer);
    }

    public void login(String dealerName, String password) {
        dealerRepository.login(dealerName, password);
    }

    public Product addProduct(Product product) {
        return dealerRepository.addProduct(product);
    }

    public Product updateProduct(Product product) {
        return dealerRepository.updateProduct(product);
    }

    public void deleteProduct(Long productId) {
        dealerRepository.deleteProduct(productId);
    }

    public List<Order> getAllOrders() {
        return dealerRepository.getAllOrders();
    }

    public void approveOrder(Long orderId) {
        dealerRepository.approveOrder(orderId);
    }

    public Dealer findDealerIdByName(String name) {
        return dealerRepository.findDealerIdByName(name);
    }

	
}
