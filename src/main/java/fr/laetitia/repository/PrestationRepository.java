package fr.laetitia.repository;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Prestation;

public interface PrestationRepository extends CrudRepository<Prestation, Integer> {
	
}
