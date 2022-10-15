package com.shouvick.userservice.dto.User;

import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.validator.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationRequest {
    @UUID
    @NotBlank(message = "UserId can not be empty")
    private String userId;
    private Date createdOn;
    private LocationRequest location;

}
