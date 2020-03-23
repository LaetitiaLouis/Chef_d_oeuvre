package fr.laetitia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;

@DataJpaTest
public interface PhotoRepositoryTest {
	
//	@Autowired PhotoRepository photoRepository;
//	
//	@Autowired TestEntityManager testEntityManager;
//	
//	@Test
//	public void testFindByProjet() {
//		Photo photo = new Photo();
//		Projet projet1 = new Projet();
//		projet1.setId(1);
//		
//		Photo savedPhoto = testEntityManager.persistFlushFind(photo);
//				
//		List<Photo> photoResult = this.photoRepository.findByProjet(projet1);
//		assertThat(photoResult.size()).isEqualTo(1);
//		assertThat(photoResult.get(0).getId()).isEqualTo(savedPhoto.getId());
//		}

}
