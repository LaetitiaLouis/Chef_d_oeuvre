package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Admin  {

	@Id
	@Column(unique = true)
	private String login;

	@Column(unique = true)
	private String password;
	private String photo;

	@Column(columnDefinition = "varchar(1000)")
	private String presentation;

	@JsonIgnoreProperties("admin")
	@OneToMany(mappedBy = "admin")
	private Set<Projet> listeProjets = new HashSet<>();
	private boolean compteValide = true;
//
//	public Admin(String login, boolean compteValide, String password, String photo, String presentation, Projet listeProjets) {
//		this.login = login;
//		this.compteValide = true;
//		this.password = password;
//		this.photo = photo;
//		this.presentation = presentation;
//		this.listeProjets = (Set<Projet>) listeProjets;
//	}
//
//	public void addAdmin(Admin admin) {
//		admin.add(admin);
//		admin.getListeProjets().add(this);
//	}
//
//	@Override
//	public String toString() {
//		return "Admin{"+"id="+ login +
//				", "
//	}
//
//	private void add(Admin admin) {
//	}
}
