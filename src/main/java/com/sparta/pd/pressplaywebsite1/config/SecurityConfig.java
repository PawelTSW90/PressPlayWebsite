package com.sparta.pd.pressplaywebsite1.config;

import com.sparta.pd.pressplaywebsite1.UserBasket;
import com.sparta.pd.pressplaywebsite1.controllers.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

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
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        IndexController.userBasket.clearBasket();
                        UrlPathHelper helper = new UrlPathHelper();
                        String context = helper.getContextPath(request);
                        response.sendRedirect(context);
                    }
                })
                .permitAll()
                //go to url after failed login
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
