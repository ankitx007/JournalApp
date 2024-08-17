package com.AnkApp.journalApp.controller;


import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.service.UserService;
import com.AnkApp.journalApp.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("health-check")
    public String healthCheck(){
        return "Health Ok";
    }


    @PostMapping("signup")
    public ResponseEntity<?> singup(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("User Already Exists", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String Token = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(Token, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Incorrect Username or Password");
            return new ResponseEntity<>("User Already Exists", HttpStatus.NOT_FOUND);
        }
    }
}
