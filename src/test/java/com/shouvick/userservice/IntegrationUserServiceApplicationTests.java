package com.shouvick.userservice;

import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.dto.User.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationUserServiceApplicationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TestUserH2Repository testUserH2Repository;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1/users");
    }

    @Test
    @Sql(statements = "INSERT INTO users(user_id, created_on, email, first_name, second_name) VALUES\n" +
            "       ('7373b3c4-346e-4005-8dc4-f1112f910203', '2022-10-15 09:21:02', 'test1@test.com','test','test');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testSaveUserLocation() throws Exception {

        String uri = baseUrl.concat("/location");
        String userId = "7373b3c4-346e-4005-8dc4-f1112f910203";

        UserLocationRequest userLocationRequest = new UserLocationRequest();
        userLocationRequest.setUserId(userId);
        userLocationRequest.setCreatedOn(new Date());
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setLatitude(22.25);
        locationRequest.setLongitude(23.35);
        userLocationRequest.setLocation(locationRequest);


        UserLocationResponse userLocationResponse =restTemplate.postForObject(uri, userLocationRequest, UserLocationResponse.class);
        assertEquals(22.25, userLocationResponse.getLocation().getLatitude());
        assertEquals(23.35, userLocationResponse.getLocation().getLongitude());

    }

    @Test
    void testSaveUser() throws Exception {

        UserRequest userRequest = new UserRequest();

        userRequest.setEmail("test4@test.com");
        userRequest.setFirstName("test firstName");
        userRequest.setSecondName("test secondName");

        UserResponse userResponse =restTemplate.postForObject(baseUrl, userRequest, UserResponse.class);
        assertEquals("test4@test.com", userResponse.getEmail());

    }


    @Test
    @Sql(statements = "INSERT INTO users(user_id, created_on, email, first_name, second_name) VALUES\n" +
            "       ('c31f587d-4d7d-4155-903f-86fa69c2cba8', '2022-10-15 09:21:02', 'test2@test.com','test','test');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO locations(location_id,user_id,created_on,latitude,longitude) VALUES\n" +
            "       ('2c6b86b8-3ef8-4249-b033-aa86197ee005', 'c31f587d-4d7d-4155-903f-86fa69c2cba8', '2022-10-15 09:21:02', 22.25,23.65);", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testUserById() throws Exception {

        String userId = "c31f587d-4d7d-4155-903f-86fa69c2cba8";

        String uri = baseUrl.concat("/" + userId);

        UserLatestLocationResponse userLatestLocationResponse =restTemplate.getForObject(uri, UserLatestLocationResponse.class);
        assertEquals(userId, userLatestLocationResponse.getUserId());

    }

    @Test
    @Sql(statements = "INSERT INTO users(user_id, created_on, email, first_name, second_name) VALUES\n" +
            "       ('da462626-aed7-449c-98ac-c93d0a83f0f6', '2022-10-15 09:21:02', 'test3@test.com','test','test');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO locations(location_id,user_id,created_on,latitude,longitude) VALUES\n" +
            "       ('90c30698-f2cf-4f67-95fa-ede94fb8101f', 'da462626-aed7-449c-98ac-c93d0a83f0f6', '2022-10-15 09:21:02', 22.25,23.65);", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testUserByDateRange() throws Exception {


        String start_date = "2022-09-01";
        String end_date = "2022-11-01";
        String userId = "da462626-aed7-449c-98ac-c93d0a83f0f6";

        String uri = baseUrl.concat("/" + userId).concat("?start_date=" + start_date).concat("&end_date=" + end_date);

        UserLocationDateRangeResponse userLocationDateRangeResponse =restTemplate.getForObject(uri, UserLocationDateRangeResponse.class);
        assertEquals(4, testUserH2Repository.findAll().size());

    }




}
