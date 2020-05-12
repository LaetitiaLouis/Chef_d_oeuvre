package fr.laetitia.repository;

import fr.laetitia.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query("SELECT c from Client c WHERE c.nom LIKE CONCAT('%',:nom,'%') OR c.prenom LIKE CONCAT('%', :prenom,'%')")
    List<Client> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
}
