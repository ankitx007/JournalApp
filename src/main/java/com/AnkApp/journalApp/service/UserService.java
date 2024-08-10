package com.AnkApp.journalApp.service;


import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            User save = userRepository.save(user);
            log.info("User saved successfully '{}' with id '{}'", user.getUserName(), save.getId());
            return true;
        }catch(Exception e){
            log.error("Error occurred while saving the user {}: ", user.getUserName());
            log.debug("Error occurred while saving the user {}: ", user.getUserName());
            return false;
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList("ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user){
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserbyId(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean deletebyId(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
