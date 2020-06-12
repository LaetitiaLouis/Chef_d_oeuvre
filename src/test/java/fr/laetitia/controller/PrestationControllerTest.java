package fr.laetitia.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import fr.laetitia.model.Message;
import fr.laetitia.model.Photo;
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
import fr.laetitia.model.Projet;
import fr.laetitia.repository.PrestationRepository;
import fr.laetitia.repository.ProjetRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PrestationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	PrestationRepository prestationRepository;

	@MockBean
	ProjetRepository projetRepository;

	private final Prestation prestation = new Prestation();
	private final String BASE_URL = "/prestations";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		prestation.setId(1);
	}

	@Test
	private void testGetAll() throws Exception {
		List <Prestation> prestations = new ArrayList<>();
		prestations.add(prestation);
		when(prestationRepository.findAll()).thenReturn(prestations);

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1));

		when(this.prestationRepository.findAll()).thenReturn(new ArrayList<>());

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isNotFound());
	}


	@Test
	public void testNew() throws Exception {
		when(prestationRepository.save(prestation)).thenReturn(prestation);
		when(prestationRepository.findById(1)).thenReturn(Optional.of(prestation));
		prestation.setId(2);

		mockMvc.perform(post(BASE_URL).accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(prestation))).andExpect(status().isCreated());

		prestation.setId(1);
		mockMvc.perform(post(BASE_URL).accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(prestation))).andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {
		when(prestationRepository.save(prestation)).thenReturn(prestation);
		when(prestationRepository.findById(1)).thenReturn(Optional.of(prestation));

		mockMvc.perform(
				put(BASE_URL).contentType(JSON).content(objectMapper.writeValueAsString(prestation)))
				.andExpect(status().isCreated());

		prestation.setId(2);
		mockMvc.perform(
				put(BASE_URL).contentType(JSON).content(objectMapper.writeValueAsString(prestation)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDelete() throws Exception {
		doNothing().when(prestationRepository).deleteById(1);
		this.mockMvc.perform(delete(BASE_URL + "/1")).andExpect(status().isOk());
	}
}
