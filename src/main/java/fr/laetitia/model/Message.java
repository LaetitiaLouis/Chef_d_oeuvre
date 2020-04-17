package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    private Client client;

    public Message(String objet, String contenu, boolean vu, LocalDate date, Client client) {
        this.objet = objet;
        this.contenu = contenu;
        this.vu = false;
        this.date = date;
        this.client = client;
    }

//    @Override
//    public String toString() {
//        return "Message{" +
//                "id=" + id +
//                ", objet='" + objet + '\'' +
//                ", contenu='" + contenu + '\'' +
//                ", vu=" + false +
//                ", LocalDate=" + date +
//                '}';
//    }
}
