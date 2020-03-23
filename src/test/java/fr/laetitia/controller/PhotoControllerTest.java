package fr.laetitia.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;
import fr.laetitia.repository.PhotoRepository;
import fr.laetitia.repository.ProjetRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PhotoControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	PhotoRepository photoRepository;

	@MockBean
	private ProjetRepository projetRepository;
	private final Photo photo = new Photo();
	private final String BASE_URL = "/api/photo";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		photo.setId(1);
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL + "/delete?id=1")).andExpect(status().isOk());
	}

	@Test
	public void testNew() throws Exception {
		when(photoRepository.save(photo)).thenReturn(photo);
		when(photoRepository.findById(1)).thenReturn(Optional.of(photo));
		photo.setId(2);

		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isCreated());

		photo.setId(1);
		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testFindByProjet() throws Exception {
		Projet projet = new Projet();
		projet.setId(1);
		projet.setPhotos(List.of(photo));
		when(projetRepository.findById(1)).thenReturn(Optional.of(projet));

		mockMvc.perform(get(BASE_URL + "/findByProjet?id=1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1));

		mockMvc.perform(get(BASE_URL + "/findByProjet?id=32")).andExpect(status().isNotFound());

		projet.setPhotos(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByProjet?id=1")).andExpect(status().isNotFound());
	}

}
