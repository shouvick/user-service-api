package com.shouvick.userservice.dto.User;

import com.shouvick.userservice.dto.Location.LocationListRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserLocationDateRangeResponse {
    private String userId;
    private List<LocationListRequest> locations;

}
