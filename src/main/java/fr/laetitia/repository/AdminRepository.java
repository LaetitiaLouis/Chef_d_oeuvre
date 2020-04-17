package fr.laetitia.repository;

import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Admin;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, String>{
    Optional<Admin> findByLogin (String login);
}
