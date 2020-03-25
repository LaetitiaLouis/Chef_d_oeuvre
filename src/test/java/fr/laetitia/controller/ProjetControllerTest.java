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
import fr.laetitia.model.Type;
import fr.laetitia.repository.PrestationRepository;
import fr.laetitia.repository.ProjetRepository;
import fr.laetitia.repository.TypeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	ProjetRepository projetRepository;

	@MockBean
	TypeRepository typeRepository;

	@MockBean
	PrestationRepository prestationRepository;

	private final Projet projet = new Projet();
	private final String BASE_URL = "/projet";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		projet.setId(1);
		projet.setIntitule("Maison1");
	}

	@Test
	public void testGetAll() throws Exception {
		when(projetRepository.findAll()).thenReturn(List.of(projet));

		this.mockMvc.perform(get(BASE_URL + "/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].intitule").value("Maison1"));

		when(this.projetRepository.findAll()).thenReturn(new ArrayList<>());

		this.mockMvc.perform(get(BASE_URL + "/")).andExpect(status().isNotFound());
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL + "/delete?id=1")).andExpect(status().isOk());
	}

	@Test
	public void testNew() throws Exception {
		when(projetRepository.save(projet)).thenReturn(projet);
		when(projetRepository.findById(1)).thenReturn(Optional.of(projet));
		projet.setId(2);

		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isCreated());

		projet.setId(1);
		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {
		when(projetRepository.save(projet)).thenReturn(projet);
		when(projetRepository.findById(1)).thenReturn(Optional.of(projet));

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isCreated());

		projet.setId(2);
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testFindByType() throws Exception {
		Type type = new Type();
		type.setId(10);
		type.setProjets(List.of(projet));
		when(typeRepository.findById(10)).thenReturn(Optional.of(type));

		mockMvc.perform(get(BASE_URL + "/findByType?type=10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1));

		mockMvc.perform(get(BASE_URL + "findByType?type=2")).andExpect(status().isNotFound());

		type.setProjets(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByType?type=10")).andExpect(status().isNotFound());

	}

	@Test
	public void testFindByPrestation() throws Exception {
		Prestation prestation = new Prestation();
		prestation.setId(10);
		prestation.setProjets(List.of(projet));
		when(prestationRepository.findById(10)).thenReturn(Optional.of(prestation));

		mockMvc.perform(get(BASE_URL + "/findByPrestation?prestation=10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1));

		mockMvc.perform(get(BASE_URL + "/findByPrestation?prestation=2")).andExpect(status().isNotFound());

		prestation.setProjets(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByPrestation?prestation=10")).andExpect(status().isNotFound());

	}

}
