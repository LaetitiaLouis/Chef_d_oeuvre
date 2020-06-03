package fr.laetitia.repository;

import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Set<Photo> findByCategorie(String categorie);

    List<Photo> findByProjet(Projet projet);

}
