package cz.applifting.endpointsmonitoring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.applifting.endpointsmonitoring.entity.User;
import cz.applifting.endpointsmonitoring.repo.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Log4j2
class UserTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository repository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup(){
		repository.deleteAll();
	}

	@Test
	void shouldReturnCreated() throws Exception {


		ResultActions response = mockMvc.perform(post("/api/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(getUser())));

		response.andDo(print()).
			andExpect(status().isCreated());

	}

	@Test
	void shouldReturnCreatedUser() throws Exception {
		var saved = repository.save(getUser());
		ResultActions response = mockMvc.perform(get("/api/users/{id}", saved.getId()));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.name", is(saved.getName())))
			.andExpect(jsonPath("$.email", is(saved.getEmail())));
	}

	public User getUser() {
		User user = new User();
		user.setName("Ramesh");
		user.setEmail("ramesh@gmail.com");
		return user;
	}
}
