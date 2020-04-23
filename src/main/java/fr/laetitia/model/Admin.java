package fr.laetitia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LOUISL
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin implements UserDetails {

    @Id
    @Column(unique = true)
    private String login;

    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//permet de ne pas afficher le password dans le Json
    private String password;

    private String photo;
    private String role;
    private String prenom;

    @Column(columnDefinition = "varchar(1000)")
    private String presentation;

    @JsonIgnoreProperties("admin")
    @OneToMany(mappedBy = "admin")
    private Set<Projet> listeProjets = new HashSet<>();

    private boolean compteValide = true;


    public Admin(String login, boolean compteValide, String password, String photo, String presentation) {
        this.login = login;
        this.compteValide = true;
        this.password = password;
        this.photo = photo;
        this.presentation = presentation;
        this.role = role;
        this.prenom = prenom;
    }

    @Transient
    public void addProjet(Projet projet) {
        listeProjets.add(projet);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
