package gys.examples.oauth2.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${resourceServer.url}")
    private String resourceUrl;

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @GetMapping
    public Map get() {
        logToken(restTemplate.getAccessToken());
        return restTemplate.getForObject(resourceUrl, Map.class);
    }

    private void logToken(OAuth2AccessToken token) {
        logger.info("\nToken: \n\tType: {}\n\tExpiration: {}\n\tRefresh Token: {}\n\tScope: {}\n\tValue: {}",
                token.getTokenType(),
                token.getExpiration(),
                token.getRefreshToken(),
                token.getScope(),
                token.getValue());
    }
}
