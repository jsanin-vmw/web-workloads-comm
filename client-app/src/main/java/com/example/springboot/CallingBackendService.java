package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallingBackendService {
    Logger logger = LoggerFactory.getLogger(CallingBackendService.class);

    final RestTemplate restTemplate;

    @Value("${endpoints.server-app-url}")
    private String serverAppUrl;

    public CallingBackendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Scheduled(fixedDelay = 1000)
    public void callBackedService() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(serverAppUrl, String.class);
            logger.info("The server says: " + response.getBody());
        } catch(Exception e) {
            logger.error("Error calling server", e);
        }

    }
}
