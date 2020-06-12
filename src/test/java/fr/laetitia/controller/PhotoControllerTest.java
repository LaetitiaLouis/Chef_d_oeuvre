package fr.laetitia.controller;

		import static org.mockito.Mockito.doNothing;
		import static org.mockito.Mockito.when;
		import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
		import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
		import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
		import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
		import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

		import java.util.*;

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
@AutoConfigureMockMvc(addFilters = false)
public class PhotoControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PhotoRepository photoRepository;

	@MockBean
	private ProjetRepository projetRepository;

	private final Photo photo = new Photo();
	private final String BASE_URL = "/photos";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		photo.setId(1);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Photo> photos = new ArrayList<>();
		photos.add(photo);
		when(photoRepository.findAll()).thenReturn(photos);

		this.mockMvc.perform(get(BASE_URL + "/")).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1));

		when(this.photoRepository.findAll()).thenReturn(new ArrayList<>());
		this.mockMvc.perform(get(BASE_URL + "/")).andExpect(status().isNotFound());
	}


	@Test
	public void testNew() throws Exception {
		when(photoRepository.save(photo)).thenReturn(photo);
		when(photoRepository.findById(1)).thenReturn(Optional.of(photo));
		photo.setId(2);

		mockMvc.perform(
				post(BASE_URL).accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isCreated());

		photo.setId(1);
		mockMvc.perform(
				post(BASE_URL).accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testDelete() throws Exception {
		doNothing().when(photoRepository).deleteById(1);
		this.mockMvc.perform(delete(BASE_URL+"/1")).andExpect(status().isOk());
	}

	@Test
	public void testGetByProjet() throws Exception {
		Projet projet = new Projet();
		projet.setId(10);
		Set<Photo> photos = new HashSet<>();
		photos.add(photo);
		projet.setPhotos(photos);
		when(projetRepository.findById(10)).thenReturn(Optional.of(projet));

		mockMvc.perform(get(BASE_URL + "/projets/10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1));

		mockMvc.perform(get(BASE_URL + "/projets/2")).andExpect(status().isNotFound());

		projet.setPhotos(new HashSet<>());
		mockMvc.perform(get(BASE_URL + "/projets/10")).andExpect(status().isNotFound());
	}

	@Test
	public void testGetById () throws Exception {
		when(photoRepository.findById(1)).thenReturn(Optional.of(photo));
		mockMvc.perform(get(BASE_URL + "/1")).andExpect(status().isOk())
				.andExpect(jsonPath("id").value(1));
		mockMvc.perform(get(BASE_URL+"/2")).andExpect(status().isNotFound());
	}

	@Test
	public void testGetByCategorie() throws Exception {
		Set<Photo> photos = new HashSet<>();
		photos.add(photo);
		when(this.photoRepository.findByCategorie("Accueil")).thenReturn(photos);
		this.mockMvc.perform(get(BASE_URL + "/byCategorie?categorie=Accueil")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].categorie").value(photo.getCategorie()));
		mockMvc.perform(get(BASE_URL+"/byCategorie?categorie=Projet")).andExpect(status().isNotFound());
	}
}
