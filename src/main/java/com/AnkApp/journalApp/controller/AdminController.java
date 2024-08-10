package com.AnkApp.journalApp.controller;


import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getUsers();
        if (!users.isEmpty() && users != null) {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
