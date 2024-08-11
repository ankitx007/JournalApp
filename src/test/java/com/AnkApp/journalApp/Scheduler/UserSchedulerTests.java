package com.AnkApp.journalApp.Scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=dev")
public class UserSchedulerTests {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void fetchUsersAndSendSAMailTest(){
        userScheduler.fetchUsersAndSendSAMail();
    }
}
