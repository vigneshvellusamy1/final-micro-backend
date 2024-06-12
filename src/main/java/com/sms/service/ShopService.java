package com.sms.service;

import com.sms.model.Product;
import com.sms.model.Shop;
import com.sms.repository.ProductRepository;
import com.sms.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
    
    @Autowired
    private ProductRepository productRepository; 

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop addShop(Shop shop) {
    	shop.setStatus("Pending");
        return shopRepository.addShop(shop);
    }

    public Shop login(String shopName, String password) {
        return shopRepository.findByShopNameAndPasswordAndStatus(shopName, password, "Active");
    }

    public Shop findByName(String name) {
        return shopRepository.findByShopName(name);
    }

	public boolean validateQuantity(Long id, int quantity) {
		Product product = productRepository.findById(id);
    	int initialQuantity = product.getProductQuantity();
    	
    	if(quantity>initialQuantity) {
    		return false;
    	}else {
    		return true;
    	}
	}
}
