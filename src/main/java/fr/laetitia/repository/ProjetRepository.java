package fr.laetitia.repository;


        import fr.laetitia.model.Projet;
        import fr.laetitia.model.Type;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;
        import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    Optional<Projet> findByIntitule(String intitule);
    List<Projet> findByTypeLibelleContainingOrIntituleContainingAllIgnoreCase(String typeProjet, String intitule);
}
