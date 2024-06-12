package com.sms.model;

 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
 

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String productType;
    private double price;
    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "dealerId")
    @JsonIgnoreProperties(value={"products"})
    private Dealer dealer;
    
    

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long productId, String name, String productType, double price, int productQuantity, Dealer dealer) {
		super();
		this.productId = productId;
		this.name = name;
		this.productType = productType;
		this.price = price;
		this.productQuantity = productQuantity;
		this.dealer = dealer;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

    

    
}
