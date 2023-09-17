package com.vi5hnu.blogapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(6,new SecureRandom());
//        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();//default strength 10,
        System.out.println(passwordEncoder.encode("vishnu"));
        System.out.println(passwordEncoder.encode("krishan"));
    }
}
