package com.shouvick.userservice.util;

import com.shouvick.userservice.dto.Location.LocationListRequest;
import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.dto.User.UserLatestLocationResponse;
import com.shouvick.userservice.dto.User.UserLocationDateRangeResponse;
import com.shouvick.userservice.dto.User.UserResponse;
import com.shouvick.userservice.entity.Location;
import com.shouvick.userservice.entity.User;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserDTOConverter {

    public UserResponse convertToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    public UserLatestLocationResponse convertToUserLatestLocationDto(User user) {
        UserLatestLocationResponse userLatestLocationResponse = new UserLatestLocationResponse();
        userLatestLocationResponse.setUserId(user.getUserId().toString());
        userLatestLocationResponse.setEmail(user.getEmail());
        userLatestLocationResponse.setCreatedOn(user.getCreatedOn());
        userLatestLocationResponse.setFirstName(user.getFirstName());
        userLatestLocationResponse.setSecondName(user.getSecondName());
        LocationRequest locationRequest = null;
        for (Location location : user.getLocations()) {
            locationRequest = new LocationRequest(location.getLatitude(), location.getLongitude());
        }
        userLatestLocationResponse.setLocation(locationRequest);
        return userLatestLocationResponse;
    }

    public UserLocationDateRangeResponse convertToUserLocationDateRangeDto(User user) {
        UserLocationDateRangeResponse userLocationDateRangeResponse = new UserLocationDateRangeResponse();
        List<LocationListRequest> locationRequests = new ArrayList<>();
        for (Location location : user.getLocations()) {
            LocationListRequest locationListRequest = new LocationListRequest();
            locationListRequest.setCreatedOn(location.getCreatedOn());
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setLatitude(location.getLatitude());
            locationRequest.setLongitude(location.getLongitude());
            locationListRequest.setLocation(locationRequest);
            locationRequests.add(locationListRequest);
        }
        userLocationDateRangeResponse.setUserId(user.getUserId().toString());
        userLocationDateRangeResponse.setLocations(locationRequests);
        return userLocationDateRangeResponse;
    }
}
