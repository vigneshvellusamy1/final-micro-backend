package com.sms.controller;


import com.sms.model.Order;
import com.sms.model.Product;
import com.sms.model.Shop;
import com.sms.service.OrderService;
import com.sms.service.ProductService;
import com.sms.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/register")
    public Shop registerShop(@RequestBody Shop shop) {
        return shopService.addShop(shop);
    }

    @PostMapping("/login")
    public Shop loginShop(@RequestBody Shop shop) {
        return shopService.login(shop.getShopName(), shop.getPassword());
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/requestStock")
    public void requestStock(@RequestBody Order order) {
         orderService.requestStock(order);
    }

    @GetMapping("/orders/{shopId}")
    public List<Order> getOrdersByShop(@PathVariable("shopId") Long shopId) {
        return orderService.getOrdersByShop(shopId);
    }

    @GetMapping("/orders/{shopId}/confirmed")
    public List<Order> getConfirmedOrdersByShop(@PathVariable Long shopId) {
        return orderService.getConfirmedOrdersByShop(shopId);
    }
    
    @GetMapping("/getAllShops")
    public List<Shop> getAllShops(){
    	return shopService.getAllShops();
    }
    
    @GetMapping("/getShopByName/{name}")
    public Long getShopByName(@PathVariable("name")String name) {
    	Shop shop= shopService.findByName(name);
    	return shop.getShopId();
    }
    
    @PostMapping("/validateProductQuantity/{prdId}/{quantity}")
    public ResponseEntity<String> validateQuantity(@PathVariable("prdId")Long id,@PathVariable("quantity")int quantity){
    	if(shopService.validateQuantity(id, quantity)) {
    		return ResponseEntity.ok("success");
    	}else {
    		return ResponseEntity.badRequest().body("Deamanded Quantity");
    	}
    }
    
    @GetMapping("/getAllProductsByDealer/{id}")
    public List<Product> getAllProductsByDealer(@PathVariable("id")long id){
    	return productService.getAllProductByDealerId(id);
    }
}
