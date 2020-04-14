package fr.laetitia.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author LOUISL
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String objet;
	@Column(columnDefinition = "varchar(500)")
	private String contenu;
	private boolean vu = false;
	private LocalDate date;
	@ManyToOne
	private Client client;
	}
