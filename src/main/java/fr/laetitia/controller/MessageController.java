package fr.laetitia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Message;
import fr.laetitia.repository.MessageRepository;

/**
 * @author LOUISL
 *
 */
@RestController
@RequestMapping("/message")
@CrossOrigin("http://localhost:4200")
public class MessageController {

	@Autowired
	MessageRepository messageRepository;

	/**
	 * Afficher la liste des messages
	 * 
	 * @return la liste de messages si elle n'est pas vide sinon une erreur 404
	 */
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		List<Message> messages = (List<Message>) messageRepository.findAll();
		if (messages.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(messages);
		}
	}

	/**
	 * Enregistrer un objet message
	 * 
	 * @param L'objet message dans le body de la requête
	 * @return L'objet message est créé si l'id n'existe pas sinon un message et une
	 *         erreur 409
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Message message) {
		Optional<Message> p = messageRepository.findById(message.getId());
		if (p.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce message existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(messageRepository.save(message));
		}
	}

	/**
	 * Modifier un message
	 * 
	 * @param L'objet message dans le body de la requête
	 * @return L'objet message modifié ou une erreur 404 et un message s'il n'a pas
	 *         été trouvé en base de données
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Message message) {
		Optional<Message> p = messageRepository.findById(message.getId());
		if (p.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body((messageRepository.save(message)));
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Supprimer un message par son id
	 * 
	 * @param L'id du message à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProspect(@RequestParam int id) {
		messageRepository.deleteById(id);
		return ResponseEntity.ok("Message supprimé");
	}
}
