package fr.laetitia.repository;

import fr.laetitia.model.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;

//@DataJpaTest
//public interface PhotoRepositoryTest {

//    @Autowired
//    PhotoRepository photoRepository;
//
//    @Autowired
//    TestEntityManager testEntityManager;
//
//    @Test
//    public void testFindByCategorie() {
//        Photo photo = new Photo();
//        Photo photo1 = new Photo();
//        Photo photo2 = new Photo();
//
//        photo.setCategorie("accueil");
//        photo1.setCategorie("presentation projet");
//        photo2.setCategorie("accueil");
//
//        testEntityManager.persistAndFlush(photo);
//        testEntityManager.persistAndFlush(photo1);
//        testEntityManager.persistAndFlush(photo2);
//
//        Set<Photo> photoResult = this.photoRepository.findByCategorie("accueil");
//        assertThat(photoResult.size()).isEqualTo(1);
//        for (Photo currentPhoto : photoResult) {
//            assertThat("accueil").isEqualTo(currentPhoto.getCategorie());
//        }
//
//    }
//}
