package com.cydeo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication  //this includes @Configuration
public class TicketingProjectSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectSecurityApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    // I can not new PasswordEncoder because it is interface, we need to implementation class and we use object from BCryptPasswordEncoder class
        }
        // whenever we create user in UI, Abc1 password in the database we can not save as Abc1, we need to save as an encoded and
    //how I am gonna make this encoded by using this method which is coming from this object.
     }

    //whenever I add dependency about security, and PasswordEncoder class is coming, and it has a method
    //and I need to create a bean this method, and I use @Bean because I could not put the class level.
    //nobody is gonna ask me what are the methods inside the PasswordEncoder, i don't need able to explain this
    //but I need to explain line 22 to 23
    //PasswordEncoder is interface, what we need to do, we need to implementation class, you can create by using polymorphism relationship
    // if you are creating a basic bean, you can include this one inside the Runner class
// but if you are writing bigger concept, bigger bean implementation, you can write as a separate config classes we will have different config like SecurityConfig class