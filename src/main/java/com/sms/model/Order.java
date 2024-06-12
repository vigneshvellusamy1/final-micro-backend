package com.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private int productQuantity;
    private String status;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties(value={"orders"})
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @JsonIgnoreProperties(value={"orders"})
    private Dealer dealer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value={"orders"})
    private Product product;
    																																																																	
    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

	public void setApproved(boolean b) {
		
		
	}
}
