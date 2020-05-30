package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Client;
import fr.laetitia.model.Projet;
import fr.laetitia.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author LOUISL
 */

@RestController
@RequestMapping("/clients")
//@CrossOrigin("http://localhost:4200")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    /**
     * Afficher la liste des clients
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<Client> clients = clientRepository.findAll();
        if (clients.iterator().hasNext()) {
            return ResponseEntity.ok(clients);
        } else {
            return HttpResponse.NOT_FOUND;

        }
    }

    /**
     * Enregistrer un nouveau client
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Client client) {
        Optional<Client> maybeClient = clientRepository.findById(client.getId());
        if (maybeClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce client existe déjà");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(client));
        }
    }

    /**
     * Supprimer un client par son id
     */
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Client supprimé");
    }

    /**
     * Modifier un client
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Client client) {
        Optional<Client> maybeClient = clientRepository.findById(client.getId());
        if (maybeClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body((clientRepository.save(client)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce client n'existe pas");
        }
    }

    /**
     * Afficher un client via son id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    @GetMapping("/findByNomAndPrenom")
    public ResponseEntity<?> findByNomAndPrenom(@RequestParam String recherche){
        List<Client> clients = clientRepository.findByNomContainingOrPrenomContainingAllIgnoreCase(recherche, recherche);
        if (clients.isEmpty()) {
            return HttpResponse.NOT_FOUND;
        } else {
            return ResponseEntity.ok(clients);
        }
    }
}
