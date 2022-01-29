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

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name, password, user_enabled from users where user_name=?")
                /*.usersByUsernameQuery("select username, password, active from staff where username=?")*/
                .authoritiesByUsernameQuery("select user_name, user_role from users where user_name=?")
               /* .authoritiesByUsernameQuery("select username, user_role from staff where username=?")*/
                .passwordEncoder(encoder);


    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //only staff has access to about url
                .antMatchers("/about").hasAnyAuthority("STAFF")
                .antMatchers("/single-film").authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout().permitAll()
                //go to url after failed login
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
