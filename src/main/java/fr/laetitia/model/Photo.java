package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author LOUISL
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String categorie;
    private String lien;

    @JsonIgnoreProperties("photos")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Projet projet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id;
    }

    public Photo(String nom, String categorie, String lien, Projet projet) {
        this.nom = nom;
        this.categorie = categorie;
        this.lien = lien;
        this.projet = projet;
    }
}


