package fr.laetitia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LOUISL
 *
 */

@Entity
@Getter
@Setter
public class Prestation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String intitule;
	private String contenu;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "prestation_projet", joinColumns = @JoinColumn(name = "prestation_id"), inverseJoinColumns = @JoinColumn(name = "projet_id"))
	private List<Projet> projets = new ArrayList<>();
}
