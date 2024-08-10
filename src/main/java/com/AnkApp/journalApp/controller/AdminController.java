package com.AnkApp.journalApp.controller;


import com.AnkApp.journalApp.cache.AppCache;
import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

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

    @GetMapping("clearCache")
    public ResponseEntity<?> clearAppCache(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        appCache.init();
        log.info("Cached Cleared Successfully by {}." ,userName);
        return new ResponseEntity<>("Cached Cleared Successfully ", HttpStatus.OK);
    }
}
