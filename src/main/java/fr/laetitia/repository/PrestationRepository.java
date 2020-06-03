package fr.laetitia.repository;

import fr.laetitia.model.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestationRepository extends JpaRepository<Prestation, Integer> {
}
