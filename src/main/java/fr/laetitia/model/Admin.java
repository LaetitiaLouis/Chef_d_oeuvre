package fr.laetitia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LOUISL
 *
 */

@Entity
@Getter
@Setter
public class Admin {

	@Id
	@Column(unique = true)
	private String login;
	@Column(unique = true)
	private String password;
	private String photo;
	@Column(columnDefinition = "varchar(500)")
	private String presentation;
	private Type type;
	@ManyToOne
	private List<Projet> projets = new ArrayList<>();
}
