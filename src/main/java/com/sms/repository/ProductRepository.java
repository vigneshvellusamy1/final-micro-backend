package com.sms.repository;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sms.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Product> findAllProductByDealerId(Long id) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.dealer.dealerId = :id", Product.class)
                            .setParameter("id", id)
                            .getResultList();
    }

    public Product addProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    public Product updateProduct(Product product) {
        return entityManager.merge(product);
    }

    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

	public Product findById(Long id) {
		Product product = entityManager.find(Product.class, id);
		return product;
	}
	
	

	
}

