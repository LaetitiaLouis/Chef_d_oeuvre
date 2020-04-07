package fr.laetitia.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
