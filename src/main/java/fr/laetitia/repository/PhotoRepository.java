package fr.laetitia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	public List<Photo> findByCategorie(String categorie);
}
