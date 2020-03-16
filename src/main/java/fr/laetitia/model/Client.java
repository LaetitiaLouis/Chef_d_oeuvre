package fr.laetitia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostale;
	private String ville;
	private String email;
	private String telephone;
	private String refDevis;
	private String refFacture;
	@OneToMany
	private List<Projet> projets = new ArrayList <>();
	//private List<Prestation> prestations = new ArrayList<>();
}
