package fr.laetitia.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.laetitia.model.Admin;
import fr.laetitia.repository.AdminRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	AdminRepository adminRepository;

	private final Admin admin = new Admin();
	private final String BASE_URL = "/admin";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		admin.setLogin("Nad");
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL + "/delete?login=Nad")).andExpect(status().isOk());
	}

	@Test
	public void testNew() throws Exception {
		when(adminRepository.save(admin)).thenReturn(admin);
		when(adminRepository.findById("Nad")).thenReturn(Optional.of(admin));
		admin.setLogin("New");

		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(admin)))
				.andExpect(status().isCreated());

		admin.setLogin("Nad");
		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(admin)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {
		when(adminRepository.save(admin)).thenReturn(admin);
		when(adminRepository.findById("Nad")).thenReturn(Optional.of(admin));

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(admin)))
				.andExpect(status().isCreated());

		admin.setLogin("updateNad");
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(admin)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testCheckIfLoginExists() throws Exception {
		when(adminRepository.existsById("login")).thenReturn(true);

		this.mockMvc.perform(get(BASE_URL + "/loginExists?login=login")).andExpect(content().string("true"));

		this.mockMvc.perform(get(BASE_URL + "/loginExists?login=badLogin")).andExpect(content().string("false"));

	}
}
