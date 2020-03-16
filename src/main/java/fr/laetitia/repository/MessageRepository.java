package fr.laetitia.repository;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
