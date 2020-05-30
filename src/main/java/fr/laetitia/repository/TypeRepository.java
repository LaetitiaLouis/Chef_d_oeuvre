package fr.laetitia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fr.laetitia.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

}
