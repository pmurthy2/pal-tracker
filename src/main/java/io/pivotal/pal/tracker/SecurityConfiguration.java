package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Boolean disableHttps;
    @Value("${test.user}")
    private String configUser;

    @Value("${test.password}")
    private String configPassword;

    public SecurityConfiguration(@Value("${https.disabled:false}") Boolean disableHttps) {
        this.disableHttps = disableHttps;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!disableHttps) {
            http.requiresChannel().anyRequest().requiresSecure();
        }

        //http.authorizeRequests().antMatchers("/time-entries/**").anonymous();
        http.authorizeRequests().antMatchers("/**").hasRole("USER")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();


    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(configUser).password(configPassword).roles("USER");
    }
}