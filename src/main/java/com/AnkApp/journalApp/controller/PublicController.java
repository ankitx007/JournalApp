package com.AnkApp.journalApp.controller;


import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("health-check")
    public String healthCheck(){
        return "Health Ok";
    }


    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("User Already Exists", HttpStatus.NOT_FOUND);
        }
    }
}
