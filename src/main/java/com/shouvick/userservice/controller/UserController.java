package com.shouvick.userservice.controller;

import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.dto.User.*;
import com.shouvick.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    /** Mobile App Routes **/

    @PostMapping("/location")
    public UserLocationResponse saveUserLocation(@Valid @RequestBody UserLocationRequest userLocationRequest) {
        return this.userService.saveUserLocation(userLocationRequest);
    }

    /** Web App Routes **/

    @PostMapping()
    public UserResponse saveUser(@Valid @RequestBody UserRequest userRequest) {
        return this.userService.saveUser(userRequest);
    }

    @GetMapping("/{userId}")
    public UserLatestLocationResponse getUserById(@PathVariable String userId) {
        return this.userService.getUserById(userId);
    }

    @GetMapping("/{userId}/filter")
    public UserLocationDateRangeResponse getUserByDateTimeRange(@PathVariable String userId, @RequestParam String start_date, @RequestParam String end_date ) {
        return this.userService.getUserByDateTimeRange(userId,start_date,end_date);
    }
}
