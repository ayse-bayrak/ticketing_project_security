package com.cydeo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    // SERVICE IS MECHANISM WHICH IS GONNA VALIDATE USER IS GOING TO DO THAT AUTHENTICATION, Autorization everything for me
    //I am creating Service I can use some service belongs to Spring to do validation and extends UserDetailsService interface for validation


}
