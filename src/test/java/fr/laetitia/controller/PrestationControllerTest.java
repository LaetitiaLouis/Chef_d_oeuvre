package fr.laetitia.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import fr.laetitia.model.Prestation;
import fr.laetitia.repository.PrestationRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PrestationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	PrestationRepository prestationRepository;

	private final Prestation prestation = new Prestation();
	private final String BASE_URL = "/api/prestation";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		prestation.setId(1);
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL + "/delete?id=1")).andExpect(status().isOk());
	}

	@Test
	public void testNew() throws Exception {
		when(prestationRepository.save(prestation)).thenReturn(prestation);
		when(prestationRepository.findById(1)).thenReturn(Optional.of(prestation));
		prestation.setId(2);

		mockMvc.perform(post(BASE_URL + "/new").accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(prestation))).andExpect(status().isCreated());

		prestation.setId(1);
		mockMvc.perform(post(BASE_URL + "/new").accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(prestation))).andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {
		when(prestationRepository.save(prestation)).thenReturn(prestation);
		when(prestationRepository.findById(1)).thenReturn(Optional.of(prestation));

		mockMvc.perform(
				put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(prestation)))
				.andExpect(status().isCreated());

		prestation.setId(2);
		mockMvc.perform(
				put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(prestation)))
				.andExpect(status().isNotFound());
	}

}
