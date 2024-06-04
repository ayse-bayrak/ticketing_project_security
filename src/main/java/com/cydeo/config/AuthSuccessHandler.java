package com.cydeo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    //whenever authenticate is done, it is capturing that user role
        if (roles.contains("Admin")) {
            response.sendRedirect("/user/create");
        }
        if (roles.contains("Manager")) {
            response.sendRedirect("/tas/create");
        }
        if (roles.contains("Employee")) {
            response.sendRedirect("/task/employee");
        }
        // in interview if they ask you any core Java or OOP question, you can explain with your application usage,
        // even if they ask how about if question
        //I create role list and I used if statement to check if any role contains or does not in the list
    }
}
