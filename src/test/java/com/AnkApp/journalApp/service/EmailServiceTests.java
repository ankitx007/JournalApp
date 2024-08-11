package com.AnkApp.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=dev")
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    @Disabled
    public void sendEmailTest() {
        emailService.sendEmail("ankit.sharma@spadeinfotech.net",
                "Test Email",
                "This is a test email sent to test the" + " application");
    }
}
