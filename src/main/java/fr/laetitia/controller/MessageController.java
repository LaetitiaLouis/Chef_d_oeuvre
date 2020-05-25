package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Client;
import fr.laetitia.model.Message;
import fr.laetitia.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;


/**
 * @author LOUISL
 */
@RestController
@RequestMapping("/messages")
//@CrossOrigin("http://localhost:4200")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    /**
     * Afficher la liste des messages
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<Message> messages = messageRepository.findAll();
        if (messages.iterator().hasNext()) {
            return ResponseEntity.ok(messages);
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Enregistrer un objet message
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Message message) {
//        Optional<Message> p = messageRepository.findById(message.getId());
//        if (p.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce message existe déjà");
//        } else {
            message.setDate(LocalDate.now());
        System.out.println("message");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageRepository.save(message));
    }
//    }

    /**
     * Modifier un message
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Message message) {
        Optional<Message> m = messageRepository.findById(message.getId());
        if (m.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body((messageRepository.save(message)));
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Supprimer un message par son id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProspect(@PathVariable int id) {
        messageRepository.deleteById(id);
        return ResponseEntity.ok("Message supprimé");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok(message.get());
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }
}
