package com.AnkApp.journalApp.Scheduler;

import com.AnkApp.journalApp.cache.AppCache;
import com.AnkApp.journalApp.entity.JournalEntry;
import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.enums.Sentiment;
import com.AnkApp.journalApp.repository.UserRepositoryImpl;
import com.AnkApp.journalApp.service.EmailService;
import com.AnkApp.journalApp.service.SentimentAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {


    @Autowired
    private UserRepositoryImpl userRepositoryimpl;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    //    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail() {
        List<User> users = userRepositoryimpl.getUsersForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments =
                    journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7,
                            ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                log.info("Sentiment is: {}", mostFrequentSentiment);
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days",
                        "Sentiment is: " + mostFrequentSentiment.toString());
            }
        }
    }


    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void clearAppCache() {
        appCache.init();
    }
}
