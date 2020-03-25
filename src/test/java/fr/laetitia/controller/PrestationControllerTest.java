package fr.laetitia.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
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
import fr.laetitia.model.Projet;
import fr.laetitia.repository.PrestationRepository;
import fr.laetitia.repository.ProjetRepository;

@SpringBootTest
@AutoConfigureMockMvc
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
	private final String BASE_URL = "/prestation";
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

	@Test
	public void testFindByProjet() throws Exception {
		Projet projet = new Projet();
		projet.setId(10);
		projet.setPrestations(List.of(prestation));
		when(projetRepository.findById(10)).thenReturn(Optional.of(projet));

		mockMvc.perform(get(BASE_URL + "/findByProjet?projet=10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0]id").value(1));

		mockMvc.perform(get(BASE_URL + "/findByProjet?projet=2")).andExpect(status().isNotFound());

		projet.setPrestations(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByProjet?projet=10")).andExpect(status().isNotFound());
	}

}
