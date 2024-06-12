package com.sms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sms.model.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Order> findByShopId(Long shopId) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.shop.shopId = :shopId", Order.class)
                            .setParameter("shopId", shopId)
                            .getResultList();
    }

    public List<Order> findByShopIdAndStatus(Long shopId, String status) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.shop.shopId = :shopId AND o.status = :status", Order.class)
                            .setParameter("shopId", shopId)
                            .setParameter("status", status)
                            .getResultList();
    }

    public List<Order> findByDealerId(Long dealerId) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.dealer.dealerId = :dealerId", Order.class)
                            .setParameter("dealerId", dealerId)
                            .getResultList();
    }

    public Order addOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    public void approveOrder(Long id) {
        Order order = entityManager.find(Order.class, id);
        order.setStatus("Approved");
        entityManager.merge(order);
    }

    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

	
}
