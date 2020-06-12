package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author LOUISL
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String intitule;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    @JsonIgnoreProperties("listeProjets")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Admin admin;

    @JsonIgnoreProperties("projet")
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private Set<Photo> photos = new HashSet<>();

    // méthode appelée avant la sauvegarde d'un nouveau projet auquel on y attache chaque photo
    @PrePersist
    public void prePersist() {
        for (Photo p : photos) {
            System.out.println(p.getNom());
            p.setProjet(this);
        }
    }

    // méthode appelée avant la màj d'un projet où on apelle la méthode prePersist
    @PreUpdate
    public void preUpdate() {
        prePersist();
    }

    @JsonIgnoreProperties("listeProjets")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Client client;

    @JsonIgnoreProperties("listeProjets")
    @ManyToOne
    private Type type;

    @JsonIgnoreProperties("listeProjets")
    @ManyToMany(mappedBy = "listeProjets")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Prestation> prestations = new HashSet<>();

    //Permet de comparer des objets => utile pour remove un objet d'une liste (prestation)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return id == projet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
