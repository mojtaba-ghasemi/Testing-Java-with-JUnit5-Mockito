package com.azin.testing.java.with.junit5.mockito;

import com.azin.testing.java.with.junit5.mockito.model.UserDto;
import com.azin.testing.java.with.junit5.mockito.repo.UserRepository;
import com.azin.testing.java.with.junit5.mockito.service.*;
import com.azin.testing.java.with.junit5.mockito.service.EmailNotificationException;
import com.azin.testing.java.with.junit5.mockito.service.EmailVerificationService;
import com.azin.testing.java.with.junit5.mockito.service.UserServiceException;
import com.azin.testing.java.with.junit5.mockito.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    @Mock
    EmailVerificationService emailVerificationService;

    @DisplayName("Create user with some input parameter")
    @ParameterizedTest
    @CsvSource({"'name1', 'family1', 'tel1'"})
    void testCreateUser_WhenUserDetailProvided_ReturnUserObject(String name, String family, String email) {
        //Arrange
    //    when(userRepository.save(any(UserDto.class))).thenReturn(false);
        UserDto user = new UserDto().setFirstName(name).setLastName(family).setEmail(email);

        //Act
        userService.createUser(user);
        //Assert
        assertNotNull(user.getUserId(), () -> "create user return null");
    //    verify(userRepository, times(1)).save(any(UserDto.class));
    }

    @Disabled("When repository save() method throw exception then throw userService exception")
    @CsvSource({"'name1', 'family1', 'tel1'"})
    @ParameterizedTest
    void testCreateUser_whenSaveMethodThrowException_thenThrowUserServiceException(String name, String family, String email) {
        //Arrange
   //     when(userRepository.save(any(UserDto.class))).thenThrow(RuntimeException.class);

        UserDto user = new UserDto().setFirstName(name).setLastName(family).setEmail(email);
        //Act & Assert
        Assertions.assertThrows(UserServiceException.class, () -> {
            userService.createUser(user);
        }, "CreateUser should have thrown UserServiceException");

        //Assert
    }

    @ParameterizedTest
    @Disabled("EmailNotificationException is handled")
    @CsvSource({"'name1', 'family1', 'tel1'"})
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException(String name, String family, String email) {

        //Arrange
      //  when(userRepository.save(any(UserDto.class))).thenReturn(true);

        Mockito.doThrow(EmailNotificationException.class)
                .when(emailVerificationService).sendEmail(any(UserDto.class));
        UserDto user = new UserDto().setFirstName(name).setLastName(family).setEmail(email);

        // we can bypass a void method same as ...
        //doNothing().when(emailVerificationService).sendEmail(any(User.class)); // bypass the emailVerificationService

        //Act & Assert
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(user);
        }, "CreateUser should have thrown UserServiceException");

        //Assert
        verify(emailVerificationService, times(1)).sendEmail(any(UserDto.class));
    }

    @ParameterizedTest
    @Disabled("EmailNotificationException is executed really")
    @CsvSource({"'name1', 'family1', 'tel1'"})
    void testCreateUser_whenUserCreated_realSendEmailShouldbeCall(String name, String family, String email) {
        //Arrange
   //     when(userRepository.save(any(UserDto.class))).thenReturn(true); //bypass save method in repository class
        UserDto user = new UserDto().setFirstName(name).setLastName(family).setEmail(email);


        // I have an error here: send mehtod is a normal method but Mockito rise an error and said it is abstract method

      //  doCallRealMethod().when(emailVerificationService).sendEmail(any(User.class));

        //Act
        userService.createUser(user);

        //Assert
        verify(emailVerificationService, times(1)).sendEmail(any(UserDto.class));
    }
//    @ParameterizedTest
//    @CsvSource({"'', 'family1', 'tel1'"})
//    void testCreateUser_WhenFirstNameEmpty_returnException(String name, String family, String tel) {
//        //Arrange
//        User user = new User().setName(name).setFamily(family).setTel(tel);
//
//
//        //Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.createUser(user);
//        }, "Empty user firstName should throw exception");
//
//        //Assert
//    }
}
