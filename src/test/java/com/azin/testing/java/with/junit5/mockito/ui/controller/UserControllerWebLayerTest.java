package com.azin.testing.java.with.junit5.mockito.ui.controller;


import com.azin.testing.java.with.junit5.mockito.model.UserDto;
import com.azin.testing.java.with.junit5.mockito.service.UserService;
import com.azin.testing.java.with.junit5.mockito.ui.controller.UserController;
import com.azin.testing.java.with.junit5.mockito.ui.request.UserDetailRequestModel;
import com.azin.testing.java.with.junit5.mockito.ui.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@SpringBootTest
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//@AutoConfigureMockMvc(addFilters = false)
//@MockBean({UserServiceImpl.class})
public class UserControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    //@Autowired
    UserService userService;

    @Test
    @DisplayName("create user test")
    void testCreateUser_withValidParams_returnValidObject() throws Exception {
        //Arrange
        UserDetailRequestModel userDetailRequestModel = new UserDetailRequestModel();
        userDetailRequestModel.setFirstName("name1")
                .setLastName("family1")
                .setEmail("test@gmail.com")
                .setPassword("passghfhg1")
                .setRepeatPassword("passghfhg1");

        // bypass userService.createUser
        UserDto userDto = new ModelMapper().map(userDetailRequestModel, UserDto.class);
        userDto.setUserId("2");
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);
        // ********************

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailRequestModel));

        //Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

        UserResponse userResponse = new ObjectMapper().readValue(responseBodyAsString, UserResponse.class);

        //Assert
        Assertions.assertFalse(userResponse.getUserId().isEmpty(), "userId is empty");
        Assertions.assertEquals(userDetailRequestModel.getFirstName(), userResponse.getFirstName(), () -> "first name is incorrect");
        //... check other user fields
    }

    @Test
    @DisplayName("create user test. when email is incorrect")
    void testCreateUser_withInvalidEmail_return400StatusCode() throws Exception {
        //Arrange
        UserDetailRequestModel userDetailRequestModel = new UserDetailRequestModel();
        userDetailRequestModel.setFirstName("name1")
                .setLastName("family1")
                .setEmail("test")
                .setPassword("passghfhg1")
                .setRepeatPassword("passghfhg1");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailRequestModel));

        //Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        // HttpStatus.BAD_REQUEST.value() == 400 status code
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(), () -> "should return an 400 status code");
    }
}
