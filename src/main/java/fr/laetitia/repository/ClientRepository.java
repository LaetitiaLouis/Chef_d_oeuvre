package fr.laetitia.repository;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

}
