package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.*;
import fr.laetitia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author LOUISL
 */
@RestController
@RequestMapping("/projets")
//@CrossOrigin("http://localhost:4200")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    PrestationRepository prestationRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    AdminRepository adminRepository;

    /**
     * Affiche la liste de tous les projets
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<Projet> projets = projetRepository.findAll();
        if (projets.iterator().hasNext()) {
            return ResponseEntity.ok(projets);
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Modifier un projet
     */
    @PutMapping
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Projet projet) {
//        vérifie si projet est présent et le récupère
        Optional<Projet> maybeProjet = projetRepository.findById(projet.getId());
        // si le projet est présent alors on supprime les photos
        if (maybeProjet.isPresent()) {
            List<Photo> photosAsupprimer = photoRepository.findByProjet(maybeProjet.get());
            for(Photo p: projet.getPhotos()) {
                photosAsupprimer.remove(p);
            }
            photoRepository.deleteAll(photosAsupprimer);
            //on sauvegarde le projet modifié avec les nouvelles photos => voir méthode prePersist dans model projet
            return ResponseEntity.status(HttpStatus.CREATED).body(projetRepository.save(projet));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce projet n'existe pas");
        }
    }

    /**
     * Enregistrer un projets
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Projet projet) {
        Optional<Projet> maybeProjet = projetRepository.findByIntitule(projet.getIntitule());
        if (maybeProjet.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce projet existe déjà");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(projetRepository.save(projet));
        }
    }

    /**
     * Supprimer un projet par son id
     * suppression du projet dans chacune des prestationhs qui contient
     * sauvegarde de la presttion modifiée
     * suppression du projet
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable int id) {
        Projet projet = projetRepository.findById(id).get();
        for (Prestation p : projet.getPrestations()) {
            p.getListeProjets().remove(projet);
            prestationRepository.save(p);
        }
        projetRepository.deleteById(id);
        return ResponseEntity.ok("Projet supprimé");
    }


    /**
     * Obtenir les projets par type
     */
    @GetMapping("/types/{typeId}")
    public ResponseEntity<?> findByType(@PathVariable int typeId) {
        Optional<Type> t = typeRepository.findById(typeId);
        if (t.isPresent()) {
            Set<Projet> listeProjets = t.get().getListeProjets();
            if (listeProjets.isEmpty()) {
                return HttpResponse.NOT_FOUND;
            } else {
                return ResponseEntity.ok(listeProjets);
            }
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Obtenir les projets par prestation
     */
    @GetMapping("/prestations/{prestationId}")
    public ResponseEntity<?> findByPrestation(@PathVariable int prestationId) {
        Optional<Prestation> p = prestationRepository.findById(prestationId);
        if (p.isPresent()) {
            Set<Projet> listeProjets = p.get().getListeProjets();
            if (listeProjets.isEmpty()) {
                return HttpResponse.NOT_FOUND;
            } else {
                return ResponseEntity.ok(listeProjets);
            }
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    @GetMapping("/admins/{adminLogin}")
    public ResponseEntity<?> findByAdmin(@PathVariable String adminLogin) {
        Optional<Admin> admin = adminRepository.findByLogin(adminLogin);
        if (admin.isPresent()) {
            Set<Projet> listeProjets = admin.get().getListeProjets();
            if (listeProjets.isEmpty()) {
                return HttpResponse.NOT_FOUND;
            } else {
                return ResponseEntity.ok(listeProjets);
            }
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Afficher un projet via son id
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Projet> projet = projetRepository.findById(id);
        if (projet.isPresent()) {
            return ResponseEntity.ok(projet.get());
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

//    @GetMapping("/{intitule}")
//    public ResponseEntity<?> findByIntitule(@PathVariable String intitule) {
//        Optional<Projet> projet = projetRepository.findByIntitule(intitule);
//        if (projet.isPresent()) {
//            return ResponseEntity.ok(projet.get());
//        } else {
//            return HttpResponse.NOT_FOUND;
//        }
//    }

    @GetMapping("/findByTypeAndIntitule")
    public ResponseEntity<?> findByTypeAndIntitule(@RequestParam String recherche) {
//        recherche = recherche.toLowerCase();
//        System.out.println(recherche);
        List<Projet> projets = projetRepository.findByTypeContainingOrIntituleContainingAllIgnoreCase(recherche, recherche);
        if (projets.isEmpty()) {
            return HttpResponse.NOT_FOUND;
        } else {
            return ResponseEntity.ok(projets);
        }
    }
}

