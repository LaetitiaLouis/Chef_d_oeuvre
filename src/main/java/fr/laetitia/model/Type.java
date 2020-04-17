package fr.laetitia.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;


    @JsonIgnoreProperties("type")
    @OneToMany(mappedBy = "type")
    private Set<Projet> listeProjets = new HashSet<>();

    public Type(String libelle) {
        this.libelle = libelle;
    }

    @Transient
    public void addProjet(Projet projet) {
        listeProjets.add(projet);
    }
}
