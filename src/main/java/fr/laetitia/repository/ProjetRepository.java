package fr.laetitia.repository;


import fr.laetitia.model.Projet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository extends CrudRepository<Projet, Integer> {

    Optional<Projet> findByIntitule(String intitule);

    @Query("SELECT p from Projet p WHERE p.type.libelle LIKE CONCAT('%',:typeProjet,'%') OR p.intitule LIKE CONCAT('%', :intitule,'%')")
    List<Projet> findByTypeAndIntitule(@Param("typeProjet")String typeProjet, @Param("intitule") String intitule);

}
