package com.xvitcoder.springmvcangularjs.config;

import com.xvitcoder.springmvcangularjs.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by trvler135 on 04.01.2017.
 */

@Configuration
@ComponentScan("com.xvitcoder.springmvcangularjs")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name="authService")
    private AuthService authService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.userDetailsService(authService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/**").access("hasRole('ROLE_USER')").and().formLogin();
        http.authorizeRequests().antMatchers("/login*").anonymous().anyRequest().authenticated().and().formLogin().loginPage("/login").and().csrf().disable();
    }
}
