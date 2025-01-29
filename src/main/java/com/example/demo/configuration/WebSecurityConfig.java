package com.example.demo.configuration;

import com.example.demo.service.user_service.UserServiceImpl;
import com.example.demo.utils.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().mvcMatchers("/myTickets").access("hasAnyRole('ROLE_CUSTOMER')");
        http.authorizeRequests().mvcMatchers("/createEvent").access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests().mvcMatchers("/places").access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests().mvcMatchers("/createPlace").access("hasAnyRole('ROLE_ADMIN')");



//        http.authorizeRequests()
//                .mvcMatchers("/login").permitAll()
//                .anyRequest().authenticated();


        http.formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
               .successHandler(customAuthenticationSuccessHandler)
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password");


        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.exceptionHandling()
                .accessDeniedPage("/403");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

}
