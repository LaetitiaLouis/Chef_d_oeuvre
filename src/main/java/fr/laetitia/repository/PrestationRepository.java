package fr.laetitia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Prestation;
import fr.laetitia.model.Projet;

public interface PrestationRepository extends CrudRepository<Prestation, Integer> {
	//public List<Prestation> findByProject(Projet projet);

}
