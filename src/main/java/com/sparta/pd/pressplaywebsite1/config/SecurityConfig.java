package com.sparta.pd.pressplaywebsite1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SecurityConfig(DataSource dataSource, BCryptPasswordEncoder encoder) {
        this.dataSource = dataSource;
        this.encoder = encoder;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("Pawel").password("{noop}pawel").authorities("STAFF");
        auth.inMemoryAuthentication().withUser("Kieran").password("{noop}kieran").authorities("USER");*/
auth.jdbcAuthentication()
        .dataSource(dataSource);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //only staff has access to about url
                .antMatchers("/about").hasAnyAuthority("STAFF")
                //always use homepage after success login
                .and().formLogin().defaultSuccessUrl("/", true)
                //go to url after failed login
                .and().exceptionHandling().accessDeniedPage("/access-denied");
    }
}
