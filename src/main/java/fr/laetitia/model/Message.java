package fr.laetitia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author LOUISL
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
    private boolean statutClient = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Client client;
}
