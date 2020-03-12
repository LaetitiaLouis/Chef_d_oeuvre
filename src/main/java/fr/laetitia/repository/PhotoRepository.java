package fr.laetitia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	public List<Photo> findByProjet(Projet projet);  

}
