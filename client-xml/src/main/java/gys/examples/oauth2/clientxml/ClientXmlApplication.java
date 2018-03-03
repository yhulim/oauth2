package gys.examples.oauth2.clientxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientXmlApplication {

    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        RestClient restClient = (RestClient) applicationContext.getBean("restClient");

        logToken(restClient.getToken());

        Map response = restClient.get();

        if (logger.isLoggable(Level.INFO)) {
            logger.info(response.get("message").toString());
        }
    }

    private static void logToken(OAuth2AccessToken token) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(
                String.format("%nToken: %n\tType: %s%n\tExpiration: %s%n\tRefresh Token: %s%n\tScope: %s%n\tValue: %s",
                token.getTokenType(),
                token.getExpiration(),
                token.getRefreshToken(),
                token.getScope(),
                token.getValue()));
        }
    }
}
