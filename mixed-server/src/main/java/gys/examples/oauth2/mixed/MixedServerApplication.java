package gys.examples.oauth2.mixed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
@Configuration
public class MixedServerApplication extends AuthorizationServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MixedServerApplication.class, args);
    }

    /*
        Request token:
        curl client_id:client_secret@localhost:8081/oauth/token -d grant_type=client_credentials

        Request resource:
        curl -H "Authorization: Bearer $TOKEN" -v localhost:8081
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurator) throws Exception {
        configurator.inMemory()
                .withClient("client_id")
                .secret("client_secret")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(30)
                .scopes("read", "write");
    }
}
