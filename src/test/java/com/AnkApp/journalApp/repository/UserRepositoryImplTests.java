package com.AnkApp.journalApp.repository;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=dev")
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepositoryimpl;

    @Test
    @Disabled
    public void testSaveNewUser(){
        userRepositoryimpl.getUsersForSA();
    }
}
