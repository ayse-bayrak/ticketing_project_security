package com.cydeo.service.impl;

import com.cydeo.entity.User;
import com.cydeo.entity.common.UserPrincipal;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;


    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUserNameAndIsDeleted(username, false); // get to user from the database that needs to be validate
        if (user==null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(user); // for mapper convert to UserDetails object which is understand Spring
        //get the user from db,and convert to user springs understands by using userPrincipal
    // I am gonna give the User which is coming from the database to Spring by using loadUserByUsername method is coming form the UserDetailsService
    }
}
