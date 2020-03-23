package fr.laetitia.repository;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	
}
