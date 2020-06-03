package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Prestation;
import fr.laetitia.repository.ClientRepository;
import fr.laetitia.repository.PrestationRepository;
import fr.laetitia.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author LOUISL
 */

@RestController
@RequestMapping("/prestations")
public class PrestationController {

    @Autowired
    PrestationRepository prestationRepository;

    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    ClientRepository clientRepository;

    /**
     * Afficher toutes les prestations
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<Prestation> prestations = prestationRepository.findAll();
        if (prestations.iterator().hasNext()) {
            return ResponseEntity.ok(prestations);
        } else {
            return HttpResponse.NOT_FOUND;

        }
    }

    /**
     * Enregistrer une nouvelle prestation
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Prestation prestation) {
        Optional<Prestation> p = prestationRepository.findById(prestation.getId());
        if (p.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cette prestation existe déjà");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(prestationRepository.save(prestation));
        }
    }

    /**
     * Modifier une prestation
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Prestation prestation) {
        Optional<Prestation> p = prestationRepository.findById(prestation.getId());
        if (p.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(prestationRepository.save(prestation));
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Supprimer une prestation par son id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestation(@PathVariable int id) {
        prestationRepository.deleteById(id);
        return ResponseEntity.ok("Prestation supprimée");
    }
}
