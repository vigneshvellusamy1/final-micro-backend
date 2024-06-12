// /com/sms/service/AdminService.java
package com.sms.service;

import com.sms.model.Admin;
import com.sms.repository.AdminRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.getAllAdmins();
    }

    public void approveDealer(Long id) {
        adminRepository.approveDealer(id);
    }

    public void approveShop(Long id) {
        adminRepository.approveShop(id);
    }
    
    public boolean validateAdminLogin(String username, String password) {
        return adminRepository.validateAdminLogin(username, password);
    }
}
