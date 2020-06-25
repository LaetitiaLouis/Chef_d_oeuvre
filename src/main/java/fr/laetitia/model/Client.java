package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;

    @Column(unique = true)
    private String email;
    private String telephone;
    private String refDevis;
    private String refFacture;

    @JsonIgnoreProperties("client")
    @OneToMany(mappedBy = "client")
    private Set<Projet> listeProjets = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Message> messages = new HashSet<>();

    @PreRemove
    private void preRemove() {
        for (Projet p : listeProjets) {
            p.setClient(null);
        }
    }
}
