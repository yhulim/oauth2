package gys.examples.oauth2.clientxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientXmlApplication {

    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        RestClient restClient = (RestClient) applicationContext.getBean("restClient");

        Map response = restClient.get();

        if (logger.isLoggable(Level.INFO)) {
            logger.info(response.get("message").toString());
        }
    }
}
