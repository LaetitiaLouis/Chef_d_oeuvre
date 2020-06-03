package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;
import fr.laetitia.repository.PhotoRepository;
import fr.laetitia.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author LOUISL
 */
@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ProjetRepository projetRepository;

    /**
     * Afficher toutes les photos
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Photo> photos = (List<Photo>) photoRepository.findAll();
        if (photos.isEmpty()) {
            return HttpResponse.NOT_FOUND;
        } else {
            return ResponseEntity.ok(photos);
        }
    }

    /**
     * Enregistrer une photo
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Photo photo) {
        Optional<Photo> maybePhoto = photoRepository.findById(photo.getId());
        if (maybePhoto.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cette photo existe déjà");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(photoRepository.save(photo));
        }
    }

    /**
     * Supprimer une photo par son id
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable int id) {
        photoRepository.deleteById(id);
        return ResponseEntity.ok("Photo supprimée");
    }

    /**
     * Obtenir la liste des photos d'un projet
     */
    @GetMapping("/projets/{projetId}")
    public ResponseEntity<?> findByProjet(@PathVariable int projetId) {
        Optional<Projet> p = projetRepository.findById(projetId);
        if (p.isPresent()) {
            Set<Photo> listePhotos = p.get().getPhotos();
            if (listePhotos.isEmpty()) {
                return HttpResponse.NOT_FOUND;
            } else {
                return ResponseEntity.ok(listePhotos);
            }
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Obtenir une photo via son id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            return ResponseEntity.ok(photo.get());
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Obtenir la liste des photos par categorie
     */
    @GetMapping("/byCategorie")
    public ResponseEntity<?> findByCategorie(@RequestParam String categorie) {
        Set<Photo> photos = photoRepository.findByCategorie(categorie);
        if (photos.isEmpty()) {
            return HttpResponse.NOT_FOUND;
        } else {
            return ResponseEntity.ok(photos);
        }
    }
}
