package com.shouvick.userservice.dto.User;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserResponse extends UserRequest{
    private String userId;

}
