package com.shouvick.userservice.util;

import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.dto.User.UserLocationResponse;
import com.shouvick.userservice.entity.Location;
import com.shouvick.userservice.entity.User;

public class LocationDTOConverter {

    public UserLocationResponse convertToDto(User user, Location location) {
        LocationRequest locationRequest = new LocationRequest(location.getLatitude(), location.getLongitude());
        UserLocationResponse userLocationResponse = new UserLocationResponse(user.getUserId().toString(), location.getCreatedOn(), locationRequest);
        userLocationResponse.setCreatedOn(location.getCreatedOn());
        return userLocationResponse;
    }
}
