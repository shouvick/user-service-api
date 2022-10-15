package com.shouvick.userservice.dto.User;



import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class UserRequest {

    @Email
    @NotBlank(message = "Email can not be empty")
    private String email;
    @NotBlank(message = "FirstName can not be empty")
    private String firstName;
    @NotBlank(message = "SecondName can not be empty")
    private String secondName;

}
