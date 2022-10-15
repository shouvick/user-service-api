package com.shouvick.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shouvick.userservice.dto.Location.LocationRequest;
import com.shouvick.userservice.dto.User.UserLocationRequest;
import com.shouvick.userservice.dto.User.UserRequest;
import com.shouvick.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UnitUserServiceApplicationTests {

	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;


	@Test
	public void testSaveUserLocation() throws Exception {

		String userId = "7373b3c4-346e-4005-8dc4-f1112f910203";

		UserLocationRequest userLocationRequest = new UserLocationRequest();
		userLocationRequest.setUserId(userId);
		userLocationRequest.setCreatedOn(new Date());
		LocationRequest locationRequest = new LocationRequest();
		locationRequest.setLatitude(22.25);
		locationRequest.setLongitude(23.35);
		userLocationRequest.setLocation(locationRequest);
		System.out.println(userLocationRequest);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/users/location")
				.content(asJsonString(userLocationRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}



	@Test
	void testSaveUser() throws Exception {

		UserRequest userRequest = new UserRequest();

		userRequest.setEmail("test@test.com");
		userRequest.setFirstName("test firstName");
		userRequest.setSecondName("test secondName");

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/users")
						.content(asJsonString(userRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());

	}

	@Test
	void testUserById() throws Exception {


		String userId = "7373b3c4-346e-4005-8dc4-f1112f910203";

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}", userId)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}


	@Test
	void testUserByDateRange() throws Exception {


		String start_date = "2022-09-01";
		String end_date = "2022-11-01";
		String userId = "7373b3c4-346e-4005-8dc4-f1112f910203";

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}/filter", userId)
						.param("start_date", start_date)
						.param("end_date", end_date)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}



	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
