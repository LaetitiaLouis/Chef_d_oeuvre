package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LOUISL
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;

	@Column(unique = true)
	private String email;
	private String telephone;
	private String refDevis;
	private String refFacture;

	@JsonIgnoreProperties("client")
	@OneToMany(mappedBy = "client")
	private Set<Projet> listeProjets = new HashSet<>();

	@JsonIgnoreProperties("client")
	@OneToMany(mappedBy = "client")
	private Set<Message> messages = new HashSet<>();

	@PreRemove
	private void preRemove() {
		for (Projet p : listeProjets) {
			p.setClient(null);
		}
		for (Message m : messages) {
			m.setClient(null);
		}
	}

	public Client(String nom, String prenom, String adresse, String codePostal, String ville, String email, String telephone, String refDevis, String refFacture){
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.email = email;
		this.refDevis = refDevis;
		this.refFacture = refFacture;
	}

	public void addProjet(Projet projet) {
		listeProjets.add(projet);
	}

//	@Override
//	public String toString() {
//		return "Client{" +
//				"id=" + id +
//				", nom='" + nom + '\'' +
//				", prenom='" + prenom + '\'' +
//				", adresse='" + adresse + '\'' +
//				", codePostal='" + codePostal + '\'' +
//				", ville='" + ville + '\'' +
//				", email='" + email + '\'' +
//				", telephone='" + telephone + '\'' +
//				", refDevis='" + refDevis + '\'' +
//				", refFacture='" + refFacture + '\'' +
//				'}';
//	}
}
