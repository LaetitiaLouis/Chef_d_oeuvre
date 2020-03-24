package fr.laetitia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Admin {

	@Id
	@Column(unique = true)
	private String login;
	@Column(unique = true)
	private String password;
	private String photo;
	@Column(columnDefinition = "varchar(1000)")
	private String presentation;
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<Projet> projets = new ArrayList<>();
}
