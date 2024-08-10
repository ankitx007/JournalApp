package com.AnkApp.journalApp.service;

import com.AnkApp.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Disabled
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("Ankit"));
    }

    @ParameterizedTest
    @Disabled
    @CsvSource({
            "1,0,1",
            "1,1,2",
            "15,22,37"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
