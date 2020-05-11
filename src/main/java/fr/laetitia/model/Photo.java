package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Projet projet;

//    @PreRemove
//    private void preRemove (){
//        for (Projet p : projet){
//            p.setPhotos(null);
//        }
//    }


    public Photo(String nom, String categorie, String lien, Projet projet) {
        this.nom = nom;
        this.categorie = categorie;
        this.lien = lien;
        this.projet = projet;
    }
}


