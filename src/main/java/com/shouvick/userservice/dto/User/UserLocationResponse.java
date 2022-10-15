package com.shouvick.userservice.dto.User;

import com.shouvick.userservice.dto.Location.LocationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationResponse {
    private String userId;
    private Date createdOn;
    private LocationRequest location;

}
