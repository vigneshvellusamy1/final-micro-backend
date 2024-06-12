package com.sms.controller;

import com.sms.model.Admin;
import com.sms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/approve/dealer/{id}")
    public void approveDealer(@PathVariable("id") Long id) {
        adminService.approveDealer(id);
    }

    @PutMapping("/approve/shop/{id}")
    public void approveShop(@PathVariable("id") Long id) {
        adminService.approveShop(id);
    }
    
    @PostMapping("/validateAdmin")
    public ResponseEntity<String> validateAdmin(@RequestBody Admin admin){
    	if(adminService.validateAdminLogin(admin.getUsername(), admin.getPassword())) {
    		return ResponseEntity.ok("valid user");
    	}else {
    		return ResponseEntity.badRequest().body("Invalid user");
    	}
    }
}
