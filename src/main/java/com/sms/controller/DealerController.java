package com.sms.controller;

import com.sms.model.Dealer;
import com.sms.model.Order;
import com.sms.model.Product;
import com.sms.service.DealerService;
import com.sms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/dealer")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/register")
    public Dealer registerDealer(@RequestBody Dealer dealer) {
        return dealerService.addDealer(dealer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDealer(@RequestBody Dealer dealer) {
    	try {
        dealerService.login(dealer.getDealerName(), dealer.getPassword());
        return ResponseEntity.ok("Login Success");
    	}catch ( Exception e) {
		return ResponseEntity.badRequest().body("Login Failure");
		}
    }

    @GetMapping("/all")
    public List<Dealer> getAllDealers() {
        return dealerService.getAllDealers();
    }

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody Product product) {
        return dealerService.addProduct(product);
    }

    @PutMapping("/product/update")
    public Product updateProduct(@RequestBody Product productDetails) {
        return dealerService.updateProduct(productDetails);
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        dealerService.deleteProduct(id);
    }

    @GetMapping("/orders/{dealerId}")
    public List<Order> getShopRequests() {
        return orderService.getAllOrders();
    }

    @PutMapping("/orders/approve/{orderId}")
    public void approveOrder(@PathVariable("orderId") Long orderId) {
        dealerService.approveOrder(orderId);
    }
    
    @GetMapping("/getDealerByName/{name}")
    public Long getDealerByName(@PathVariable("name")String name){
     	Dealer dealer = dealerService.findDealerIdByName(name);
     	return dealer.getDealerId();
    }
}
