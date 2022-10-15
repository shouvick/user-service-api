package com.shouvick.userservice.service;


import com.shouvick.userservice.dto.User.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserLocationResponse saveUserLocation(UserLocationRequest userLocationRequest);

    UserResponse saveUser(UserRequest userRequest);

    UserLatestLocationResponse getUserById(String userId);

    UserLocationDateRangeResponse getUserByDateTimeRange(String userId, String start_date, String end_date);
}
