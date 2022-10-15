package com.shouvick.userservice.service.impl;

import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.dto.User.*;
import com.shouvick.userservice.entity.Location;
import com.shouvick.userservice.entity.User;
import com.shouvick.userservice.exception.UserNotFoundException;
import com.shouvick.userservice.repository.LocationRepository;
import com.shouvick.userservice.repository.UserRepository;
import com.shouvick.userservice.service.UserService;
import com.shouvick.userservice.util.LocationDTOConverter;
import com.shouvick.userservice.util.UserDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserLocationResponse saveUserLocation(UserLocationRequest userLocationRequest) {
        try{
            Optional<User> user = userRepository.findById(UUID.fromString(userLocationRequest.getUserId()));
            if(user.isEmpty()) {
                throw new UserNotFoundException(userLocationRequest.getUserId());
            }
            User userData = user.get();
            Location newLocation = new Location(userLocationRequest.getLocation().getLatitude(), userLocationRequest.getLocation().getLongitude(), userData);
            this.locationRepository.save(newLocation);
            LocationDTOConverter locationDTOConverter = new LocationDTOConverter();
            UserLocationResponse userLocationResponse = locationDTOConverter.convertToDto(userData, newLocation);
            return userLocationResponse;
        }
        catch (UserNotFoundException userNotFoundException) {
            throw new UserNotFoundException(userLocationRequest.getUserId());
        }
        catch(Exception ex) {
            LOGGER.info("Error on Save user Location", ex);
        }
        return null;
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        try {
            Optional<User> exists = this.userRepository.findByEmail(userRequest.getEmail());
            User user = new User(userRequest.getEmail(),userRequest.getFirstName(),userRequest.getSecondName());
            User userData;
            if(exists.isPresent()) {
                exists.get().setFirstName(user.getFirstName());
                exists.get().setSecondName(user.getSecondName());
                userData = this.userRepository.save(exists.get());
            }
            else {
                userData = this.userRepository.save(user);
            }
            UserDTOConverter userDTOConverter = new UserDTOConverter();
            UserResponse userResponse = userDTOConverter.convertToDto(userData);
            return userResponse;
        }
        catch (Exception ex) {
            LOGGER.info("Error on Save User", ex);
        }
        return null;
    }

    @Override
    public UserLatestLocationResponse getUserById(String userId) {
        try{
            Optional<User> user = Optional.ofNullable(this.userRepository.findLatestById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
            UserDTOConverter userDTOConverter = new UserDTOConverter();
            UserLatestLocationResponse userLatestLocationResponse = userDTOConverter.convertToUserLatestLocationDto(user.get());
            return userLatestLocationResponse;
        }
        catch (UserNotFoundException userNotFoundException){
            throw new UserNotFoundException(userId);
        }
        catch (Exception ex){
            LOGGER.info("Error on get User By Id", ex);
        }
       return null;
    }

    @Override
    public UserLocationDateRangeResponse getUserByDateTimeRange(String userId, String start_date, String end_date) {
        try{
            Optional<User> user = Optional.ofNullable(this.userRepository.findDateRangeById(userId, start_date, end_date).orElseThrow(() -> new UserNotFoundException(userId)));
            UserDTOConverter userDTOConverter = new UserDTOConverter();
            UserLocationDateRangeResponse userLocationDateRangeResponse = userDTOConverter.convertToUserLocationDateRangeDto(user.get());
            return userLocationDateRangeResponse;
        }
        catch (UserNotFoundException userNotFoundException){
            throw new UserNotFoundException(userId);
        }
        catch (Exception ex){
            LOGGER.info("Error on user location Date Range", ex);
        }
        return null;
    }
}
