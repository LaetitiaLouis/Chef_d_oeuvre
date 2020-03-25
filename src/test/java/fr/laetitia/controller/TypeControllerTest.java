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

import fr.laetitia.model.Type;
import fr.laetitia.repository.TypeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TypeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	TypeRepository typeRepository;

	private final Type type = new Type();
	private final String BASE_URL = "/type";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		type.setId(1);
		type.setLibelle("piscine");
	}

	@Test
	public void testGetAll() throws Exception {
		when(typeRepository.findAll()).thenReturn(List.of(type));

		mockMvc.perform(get(BASE_URL + "/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].libelle").value("piscine"));

		when(typeRepository.findAll()).thenReturn(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/")).andExpect(status().isNotFound());
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL + "/delete?id=1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdate() throws Exception {
		when(typeRepository.save(type)).thenReturn(type);
		when(typeRepository.findById(1)).thenReturn(Optional.of(type));

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isCreated());

		type.setId(2);
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testNew() throws Exception {
		when(typeRepository.save(type)).thenReturn(type);
		when(typeRepository.findById(1)).thenReturn(Optional.of(type));
		type.setId(2);

		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isCreated());

		type.setId(1);
		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isConflict());
	}

}
