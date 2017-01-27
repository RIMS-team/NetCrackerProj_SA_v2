package com.xvitcoder.springmvcangularjs.config;

import com.xvitcoder.springmvcangularjs.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login*").permitAll().antMatchers("/adminpass").permitAll()
                .antMatchers("/*").authenticated()
                .antMatchers("/user/getAuthorizedUser").permitAll()
                .antMatchers("/user/checkEmail").permitAll()
                .antMatchers("/user/update").permitAll()
                .antMatchers("/user/*").access("hasRole('ROLE_ADMIN')")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout=true")
                .and().rememberMe().key("uniqueAndSecret")
                .and().formLogin().loginPage("/login").failureForwardUrl("/login?error=true").and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
