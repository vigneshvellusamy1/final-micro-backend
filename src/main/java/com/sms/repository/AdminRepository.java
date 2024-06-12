package com.sms.repository;

import org.springframework.stereotype.Repository;
import com.sms.model.Admin;
import com.sms.model.Dealer;
import com.sms.model.Shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
@Transactional
public class AdminRepository {

	@Autowired
	private EntityManager entityManager;

	public List<Admin> getAllAdmins() {
		return entityManager.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
	}

	public boolean existsByUsername(String username) {
		Long count = entityManager.createQuery("SELECT COUNT(a) FROM Admin a WHERE a.username = :username", Long.class)
				.setParameter("username", username).getSingleResult();
		return count > 0;
	}

	public boolean existsByPassword(String password) {
		Long count = entityManager.createQuery("SELECT COUNT(a) FROM Admin a WHERE a.password = :password", Long.class)
				.setParameter("password", password).getSingleResult();
		return count > 0;
	}

	public void save(Admin admin) {
		entityManager.persist(admin);
	}

	public void approveDealer(Long id) {
		Dealer dealer = entityManager.find(Dealer.class, id);
		dealer.setStatus("Active");

		entityManager.merge(dealer);
	}

	public void approveShop(Long id) {
		Query<Shop> query = (Query<Shop>) entityManager.createQuery("from Shop where shopId=:id");
		query.setParameter("id", id);
		Shop shop = query.getSingleResult();
		shop.setStatus("Active");
		entityManager.merge(shop);
	}

	public boolean validateAdminLogin(String username, String password) {
		Long count = entityManager
				.createQuery("SELECT COUNT(a) FROM Admin a WHERE a.username = :username AND a.password = :password",
						Long.class)
				.setParameter("username", username).setParameter("password", password).getSingleResult();
		return count > 0;
	}
}
