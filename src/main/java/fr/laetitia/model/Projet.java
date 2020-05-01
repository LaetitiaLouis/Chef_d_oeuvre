package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String intitule;
    private String description;

    @JsonIgnoreProperties("listeProjets")
    @ManyToOne
    private Admin admin;

    @JsonIgnoreProperties("projet")
    @OneToMany(mappedBy = "projet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Photo> photos = new HashSet<>();

    @JsonIgnoreProperties("listeProjets")
    @ManyToOne
    private Client client;

    @JsonIgnoreProperties("listeProjets")
    @ManyToOne
    private Type type;

    @JsonIgnoreProperties("listeProjets")
    @ManyToMany(mappedBy = "listeProjets")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Prestation> prestations = new HashSet<>();

    public Projet(String intitule, Admin admin, Client client, Type type) {
        this.intitule = intitule;
        this.admin = admin;
        this.client = client;
        this.type = type;
    }

public void Photo(Photo photo) {
    	photos.add(photo);
}

public void Prestation(Prestation prestation) {
    	prestations.add(prestation);
}
}
