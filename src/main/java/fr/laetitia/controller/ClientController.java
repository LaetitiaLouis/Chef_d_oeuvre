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
import org.springframework.web.bind.annotation.RestController;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Client;
import fr.laetitia.repository.ClientRepository;

/**
 * @author LOUISL
 *
 */

@RestController
@RequestMapping("/client")
@CrossOrigin("http://localhost:4200")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	/**
	 * Afficher la liste des clients
	 * 
	 * return la liste de clients si elle n'est pas vide sinon une erreur 404
	 */
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		List<Client> clients = (List<Client>) clientRepository.findAll();
		if (clients.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(clients);
		}
	}

	/**
	 * Enregistrer un nouveau client
	 * 
	 * @param L'objet client dans le body de la requête
	 * @return L'objet créé ou une erreur 409 si le client existe déjà
	 */
	@PostMapping("/new")
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
	 * 
	 * @param L'id du client à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteClient(@RequestParam int id) {
		clientRepository.deleteById(id);
		return ResponseEntity.ok("Client supprimé");
	}

	/**
	 * Modifier un client
	 * 
	 * @param L'objet client dans le body de la requête
	 * @return L'objet client modifié ou une erreur 404 et un message s'il n'a pas
	 *         été trouvé en base de donnée
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Client client) {
		Optional<Client> maybeClient = clientRepository.findById(client.getId());
		if (maybeClient.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body((clientRepository.save(client)));
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
}
