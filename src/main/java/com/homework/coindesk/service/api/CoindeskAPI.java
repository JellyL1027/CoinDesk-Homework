package com.homework.coindesk.service.api;

import com.homework.coindesk.service.api.dto.CoindeskApiDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoindeskAPI {

    private static final String API_URL = "https://kengp3.github.io/blog/coindesk.json";

    private final RestTemplate restTemplate;

    public CoindeskAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CoindeskApiDto getCoindeskData() {
        return restTemplate.getForEntity(API_URL, CoindeskApiDto.class).getBody();
    }
}
