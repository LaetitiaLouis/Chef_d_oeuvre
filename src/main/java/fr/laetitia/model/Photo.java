package fr.laetitia.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Photo {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String nom;
		private String lien;
		@JsonIgnore
		@ManyToOne(cascade = CascadeType.MERGE)
		private Projet projet;
}		


