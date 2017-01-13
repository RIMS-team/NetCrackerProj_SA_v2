package com.xvitcoder.springmvcangularjs.security;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcUser;
import com.xvitcoder.springmvcangularjs.model.User;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by trvler135 on 06.12.2016.
 */
@Service("authService")
public class AuthService implements UserDetailsService {

    private Logger logger = Logger.getLogger(AuthService.class);

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        logger.debug("Entering loadUserByUsername(email=" + email + ")");
        JdbcUser jdbcUser = (JdbcUser) context.getBean("userDAO");
        User user = jdbcUser.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                user.geteMail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

}
