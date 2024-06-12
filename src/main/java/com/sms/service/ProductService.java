package com.sms.service;

import com.sms.model.Product;
import com.sms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByDealerId(Long id) {
        return productRepository.findAllProductByDealerId(id);
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteProduct(productId);
    }

	public List<Product> getAllProductByDealerId(long id) {
		return productRepository.findAllProductByDealerId(id);
	}
}
