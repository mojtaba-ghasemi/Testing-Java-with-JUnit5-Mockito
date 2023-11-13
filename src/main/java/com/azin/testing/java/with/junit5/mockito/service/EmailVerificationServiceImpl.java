package com.azin.testing.java.with.junit5.mockito.service;

import com.azin.testing.java.with.junit5.mockito.model.UserDto;

public class EmailVerificationServiceImpl implements EmailVerificationService{

    @Override
    public void sendEmail(UserDto user){
        System.out.println("EmailVerificationService send method called");
    }
}
