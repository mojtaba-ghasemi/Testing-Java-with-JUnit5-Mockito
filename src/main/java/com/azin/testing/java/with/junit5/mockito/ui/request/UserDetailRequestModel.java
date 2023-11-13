package com.azin.testing.java.with.junit5.mockito.ui.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Setter
@Getter
@Accessors(chain = true)
public class UserDetailRequestModel {

    @Size(min = 3 , message = "firstName min size is 3")
    private String firstName;

    @Size(min = 3 , message = "lastName min size is 3")
    private String lastName;

    @Email
    private String email;

    @Size(min = 6 , message = "password min size is 6")
    private String password;

    @Size(min = 6 , message = "password min size is 6")
    private String repeatPassword;

}
