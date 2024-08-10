package com.AnkApp.journalApp.service;

import com.AnkApp.journalApp.api.response.WeatherResponse;
import com.AnkApp.journalApp.cache.AppCache;
import com.AnkApp.journalApp.constants.Placeholders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

    @Value("${weather.api.key}")
    public String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather() {
        String weatherApi = appCache.AppCache.get(AppCache.keys.WEATHER_API.toString());
        String finalAPI = weatherApi.replace(Placeholders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = null;
        try {
            response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        } catch (Exception e) {
            log.info("Error occurred sir", e);
        }
        WeatherResponse body = response.getBody();
        return body;
    }
}
