package fr.laetitia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LOUISL
 *
 */

@Entity
@Getter
@Setter
//@NoArgsConstructor
public class Projet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String intitule;
	private String description;
	@ManyToOne
	private Admin admin;
	@ManyToOne
	private Type type;
	@OneToMany
	private List<Photo> photos = new ArrayList<>();
	@ManyToMany
	private List<Prestation> prestations = new ArrayList<>();
	}

