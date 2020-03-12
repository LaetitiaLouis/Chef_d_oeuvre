package fr.laetitia.repository;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Projet;

public interface ProjetRepository extends CrudRepository<Projet, Integer> {

}
