package com.azin.testing.java.with.junit5.mockito.service;

import com.azin.testing.java.with.junit5.mockito.model.UserDto;

public interface EmailVerificationService {
    void sendEmail(UserDto user);
}
