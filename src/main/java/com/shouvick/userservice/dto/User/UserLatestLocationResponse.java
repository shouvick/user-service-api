package com.shouvick.userservice.dto.User;


import com.shouvick.userservice.dto.Location.LocationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserLatestLocationResponse {
    private String userId;
    private Date createdOn;
    private String email;
    private String firstName;
    private String secondName;
    private LocationRequest location;

}
