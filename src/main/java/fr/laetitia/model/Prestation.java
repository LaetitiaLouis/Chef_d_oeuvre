package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LOUISL
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prestation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String intitule;
	private String categorie;

//	@JsonIgnoreProperties("prestations")
//	@ManyToMany(mappedBy = "prestations", cascade = CascadeType.DETACH)
	@ManyToMany(cascade= CascadeType.REMOVE)
	//@OnDelete(action = OnDeleteAction.CASCADE)
// 	@JoinTable(name = "prestation_projet", joinColumns = @JoinColumn(name = "prestation"), inverseJoinColumns = @JoinColumn(name = "projet_id"))
	private Set<Projet> listeProjets = new HashSet<>();
}
