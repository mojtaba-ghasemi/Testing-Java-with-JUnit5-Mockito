package com.azin.testing.java.with.junit5.mockito.service;

import com.azin.testing.java.with.junit5.mockito.model.UserDto;
import com.azin.testing.java.with.junit5.mockito.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto user) {
        if (user.getFirstName().isEmpty())
            throw new IllegalArgumentException("User name is empty");

        user.setUserId("1");

        try{
            //userRepository.save(user);
            //emailVerificationService.sendEmail(user);

        }catch (Exception exception){
            throw new UserServiceException(exception.getMessage());
        }

        return user;
    }
}