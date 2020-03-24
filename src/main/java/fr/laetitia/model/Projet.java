package fr.laetitia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
//@NoArgsConstructor
public class Projet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String intitule;
	private String description;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	private Admin admin;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	private Type type;
	@OneToMany(mappedBy = "projet")
	private List<Photo> photos = new ArrayList<>();
	@ManyToMany(mappedBy = "projets")
	private List<Prestation> prestations = new ArrayList<>();
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	private Client client;

}
