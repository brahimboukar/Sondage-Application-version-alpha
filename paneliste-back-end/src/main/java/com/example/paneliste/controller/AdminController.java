package com.example.paneliste.controller;

import com.example.paneliste.dto.ReqRes;
import com.example.paneliste.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping("/auth/createAdmin")
    public ResponseEntity<ReqRes> creatAdmin(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(adminService.createAdmin(reg));
    }

    @GetMapping ("/admin/get-all-user")
    public ResponseEntity<ReqRes> getAllUser() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/admin/get-user/{userId}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getUsersById(userId));
    }

    @GetMapping("/admin/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = adminService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.deleteUser(userId));
    }




}
