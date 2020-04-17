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

import fr.laetitia.model.Message;
import fr.laetitia.repository.MessageRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class MessageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MessageRepository messageRepository;

	private final Message message = new Message();
	private final String BASE_URL = "/messages";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		message.setId(1);
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL)).andExpect(status().isOk());
	}

	@Test
	public void testGetAll() throws Exception {
		when(messageRepository.findAll()).thenReturn(List.of(message));

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1));

		when(this.messageRepository.findAll()).thenReturn(new ArrayList<>());

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isNotFound());
	}

	@Test
	public void testNew() throws Exception {
		when(messageRepository.save(message)).thenReturn(message);
		when(messageRepository.findById(1)).thenReturn(Optional.of(message));
		message.setId(2);

		mockMvc.perform(post(BASE_URL).accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(message))).andExpect(status().isCreated());

		message.setId(1);
		mockMvc.perform(post(BASE_URL).accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(message))).andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {
		when(messageRepository.save(message)).thenReturn(message);
		when(messageRepository.findById(1)).thenReturn(Optional.of(message));

		mockMvc.perform(put(BASE_URL).contentType(JSON).content(objectMapper.writeValueAsString(message)))
				.andExpect(status().isCreated());

		message.setId(2);
		mockMvc.perform(put(BASE_URL).contentType(JSON).content(objectMapper.writeValueAsString(message)))
				.andExpect(status().isNotFound());
	}
}
