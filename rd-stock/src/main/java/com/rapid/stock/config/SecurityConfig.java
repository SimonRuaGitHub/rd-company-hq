package com.rapid.stock.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
              httpSecurity.cors().and()
                          .csrf().disable()
                          .authorizeRequests()
                          .antMatchers("/dummy/","/api/consumer/{id}","/actuator/**")
                          .permitAll()
                          .and()
                          .sessionManagement()
                          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}