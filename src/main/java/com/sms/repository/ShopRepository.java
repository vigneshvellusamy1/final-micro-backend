package com.sms.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sms.model.Shop;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ShopRepository {

	@Autowired
	private EntityManager entityManager;

	public Shop findByShopNameAndPasswordAndStatus(String shopName, String password, String status) {
		return entityManager.createQuery(
				"SELECT s FROM Shop s WHERE s.shopName = :shopName AND s.password = :password AND s.status = :status",
				Shop.class).setParameter("shopName", shopName).setParameter("password", password)
				.setParameter("status", status).getSingleResult();
	}

	public Shop findByShopName(String shopName) {
		return entityManager.createQuery("SELECT s FROM Shop s WHERE s.shopName = :shopName", Shop.class)
				.setParameter("shopName", shopName).getSingleResult();
	}

	public Shop addShop(Shop shop) {
		entityManager.persist(shop);
		return shop;
	}

	public List<Shop> findAll() {
		return entityManager.createQuery("SELECT s FROM Shop s", Shop.class).getResultList();
	}
	
}
