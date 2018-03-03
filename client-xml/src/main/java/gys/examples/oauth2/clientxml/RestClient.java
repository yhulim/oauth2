package gys.examples.oauth2.clientxml;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import java.util.Map;

public class RestClient {

    private String url;
    private OAuth2RestTemplate restTemplate;

    public RestClient(String url, OAuth2RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public Map get() {
        return restTemplate.getForObject(url, Map.class);
    }
}
