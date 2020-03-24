package fr.laetitia.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LOUISL
 *
 */
@Entity
@Getter
@Setter
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String expediteur;
	private String objet;
	@Column(columnDefinition = "varchar(500)")
	private String contenu;
	private boolean client;
	private Date date;
	}
