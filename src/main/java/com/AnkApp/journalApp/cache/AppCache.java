package com.AnkApp.journalApp.cache;


import com.AnkApp.journalApp.entity.ConfigJournalAppEntity;
import com.AnkApp.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> AppCache;

    @PostConstruct
    public void init(){
        AppCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: all){
            String value = configJournalAppEntity.getValue();
            String key = configJournalAppEntity.getKey();
            AppCache.put(key, value);
        }
    }
}
