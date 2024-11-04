package com.example.adminpanel.controller;

import com.example.adminpanel.entity.User;
import com.example.adminpanel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> listAll() {
        return  service.listAll();

    }
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            service.save(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        return service.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}
