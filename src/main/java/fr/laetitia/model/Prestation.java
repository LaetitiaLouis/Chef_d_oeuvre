package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LOUISL
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

    @ManyToMany
    private Set<Projet> listeProjets = new HashSet<>();

    public Prestation(String intitule, String categorie) {
        this.intitule = intitule;
        this.categorie = categorie;
    }

    public void addProjet(Projet projet) {
        listeProjets.add(projet);
    }
}
