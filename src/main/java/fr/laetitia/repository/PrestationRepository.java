package fr.laetitia.repository;

import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Prestation;

import java.util.Set;

public interface PrestationRepository extends JpaRepository<Prestation, Integer> {
}
