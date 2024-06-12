package com.sms.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sms.model.Dealer;
import com.sms.model.Order;
import com.sms.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class DealerRepository {

    @Autowired
    private EntityManager entityManager;

    public Dealer addDealer(Dealer dealer) {
        entityManager.persist(dealer);
        return dealer;
    }

    public void login(String dealerName, String password) {
        Dealer dealer = entityManager.createQuery("SELECT d FROM Dealer d WHERE d.dealerName = :dealerName AND d.password = :password AND d.status = 'Active'", Dealer.class)
                                     .setParameter("dealerName", dealerName)
                                     .setParameter("password", password)
                                     .getSingleResult();
        if (dealer == null) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public List<Dealer> getAllDealers() {
        return entityManager.createQuery("SELECT d FROM Dealer d", Dealer.class).getResultList();
    }

    public Product addProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    public Product updateProduct(Product productDetails) {
        return entityManager.merge(productDetails);
    }

    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public void approveOrder(Long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        int orderQuantity = order.getProductQuantity();
        Product product = entityManager.find(Product.class, order.getProduct().getProductId());
        int initialQuantity = product.getProductQuantity();
        int finalQuantity = initialQuantity - orderQuantity;
        product.setProductQuantity(finalQuantity);
        entityManager.merge(product);
        order.setStatus("Confirmed");
        entityManager.merge(order);
    }
    
    public Dealer findDealerIdByName(String name) {
        Dealer dealer = entityManager.createQuery("SELECT d FROM Dealer d WHERE d.dealerName = :name", Dealer.class)
                                     .setParameter("name", name)
                                     .getSingleResult();
        if (dealer != null) {
            return dealer;
        } else {
            return null;
        }
    }
}
