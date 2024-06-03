package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailService (PasswordEncoder encoder) {
//    List<UserDetails> userList = new ArrayList<>();
//    userList.add(
//            new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        userList.add(
//                new User("ayse", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))));
//    return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
               // .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAuthority("Admin")
                .antMatchers("/project/**").hasAuthority("Manager")
                .antMatchers("/task/employee/**").hasAuthority("Employee")
                .antMatchers("/task/**").hasAuthority("Manager")
               // .antMatchers("task/**").hasAnyRole("EMPLOYEE", "ADMIN") // DEFINE MORE THAN ONE ROLE
               // .antMatchers("task/**").hasAuthority("ROLE_EMPLOYEE")   // hasAuthority needs to be ROLE_
                .antMatchers(
                        "/",
                         "login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll() // anybody can access
                .anyRequest().authenticated()
                .and()
                //.httpBasic()// I want to use my own login page so I command it
                .formLogin()
                     .loginPage("/login")
                     .defaultSuccessUrl("/welcome")
                     .failureUrl("/login?error=true")
                     .permitAll()
                .and().build();
    }
}
