package gys.examples.oauth2.mixed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


@SpringBootApplication
@EnableResourceServer
public class MixedServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MixedServerApplication.class, args);
    }

    /*
        Request token:
        curl client_id:client_secret@localhost:8081/oauth/token -d grant_type=client_credentials

        Request resource:
        curl -H "Authorization: Bearer $TOKEN" -v localhost:8081
     */


    @EnableAuthorizationServer
    @Configuration
    protected static class AuthConfig extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer configurator) throws Exception {
            configurator.inMemory()
                    .withClient("client_id")
                    .secret("client_secret")
                    .authorizedGrantTypes("password")
                    .accessTokenValiditySeconds(30)
                    .scopes("read", "write");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager);
        }
    }

    @Configuration
    @EnableWebSecurity
    protected static class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("username")
                    .password("password")
                    .roles("USER");
        }
    }


}
