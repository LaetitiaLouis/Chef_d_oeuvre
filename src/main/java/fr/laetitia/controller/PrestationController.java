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
import fr.laetitia.model.Prestation;
import fr.laetitia.model.Projet;
import fr.laetitia.repository.ClientRepository;
import fr.laetitia.repository.PrestationRepository;
import fr.laetitia.repository.ProjetRepository;

/**
 * @author LOUISL
 *
 */

@RestController
@RequestMapping("/api/prestation")
@CrossOrigin("http://localhost:4200")
public class PrestationController {

	@Autowired
	PrestationRepository prestationRepository;

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	ClientRepository clientRepository;

	/**
	 * Enregistrer une nouvelle prestation
	 * 
	 * @param L'objet prestation dans le body de la requête
	 * @return L'objet créé ou une erreur 409 si elle existe déjà
	 */
	@PostMapping("/new")
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
	 * 
	 * @param L'objet prestation dans le body de la requête
	 * @return L'objet prestation modifié ou une erreur 404 et un message si elle
	 *         n'a pas été trouvé en base de données
	 */
	@PutMapping("/update")
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
	 * 
	 * @param L'id de la prestation à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deletePrestation(@RequestParam int id) {
		prestationRepository.deleteById(id);
		return ResponseEntity.ok("Prestation supprimée");
	}

	/**
	 * Obtenir les prestations par projet
	 * 
	 * @param L'objet projet souhaité
	 * @return Une liste de prestations si elle n'est pas vide sinon un message et
	 *         une erreur 404
	 */
	@GetMapping("/findByProjet")
	public ResponseEntity<?> findByProjet(@RequestParam int projet) {
		Optional<Projet> p = projetRepository.findById(projet);
		if (p.isPresent()) {
			List<Prestation> prestations = p.get().getPrestations();
			return ResponseEntity.ok(prestations);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Obtenir les prestations par client
	 * 
	 * @param L'objet client souhaité
	 * @return Une liste de prestations si elle n'est pas vide sinon un message et
	 *         une erreur 404
	 */
/*	@GetMapping("/findByClient")
	public ResponseEntity<?> findByClient(@RequestParam int client) {
		Optional<Client> c = clientRepository.findById(client);
		if (c.isPresent()) {
			List<Prestation> prestations = c.get().getPrestations();
			return ResponseEntity.ok(prestations);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}*/
}
