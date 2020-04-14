package fr.laetitia.repository;

import fr.laetitia.model.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	Set<Photo> findByCategorie(String categorie);
}
