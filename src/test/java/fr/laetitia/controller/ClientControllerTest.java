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

import fr.laetitia.model.Client;
import fr.laetitia.repository.ClientRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	ClientRepository clientRepository;
	
	private final Client client = new Client();
	private final String BASE_URL = "/clients";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		client.setId(1);
	}
	
	@Test
	public void testGetAll() throws Exception {
		when(clientRepository.findAll()).thenReturn(List.of(client));

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1));

		when(this.clientRepository.findAll()).thenReturn(new ArrayList<>());

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isNotFound());
	}

	
	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL)).andExpect(status().isOk());
	}

	@Test
	public void testNew() throws Exception {
		when(clientRepository.save(client)).thenReturn(client);
		when(clientRepository.findById(1)).thenReturn(Optional.of(client));
		client.setId(2);

		mockMvc.perform(
				post(BASE_URL).accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(client)))
				.andExpect(status().isCreated());

		client.setId(1);
		mockMvc.perform(
				post(BASE_URL).accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(client)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {
		when(clientRepository.save(client)).thenReturn(client);
		when(clientRepository.findById(1)).thenReturn(Optional.of(client));

		mockMvc.perform(put(BASE_URL).contentType(JSON).content(objectMapper.writeValueAsString(client)))
				.andExpect(status().isCreated());

		client.setId(2);
		mockMvc.perform(put(BASE_URL).contentType(JSON).content(objectMapper.writeValueAsString(client)))
				.andExpect(status().isNotFound());
	}

}
