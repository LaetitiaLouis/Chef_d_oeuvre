package fr.laetitia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Prospect;
import fr.laetitia.repository.ProspectRepository;

/**
 * @author LOUISL
 *
 */
@RestController
@RequestMapping("/api/prospect")
@CrossOrigin("http://localhost:4200")
public class ProspectController {

	@Autowired
	ProspectRepository prospectRepository;

	/**
	 * Enregistrer un objet prospect
	 * 
	 * @param L'objet prospect dans le body de la requête
	 * @return L'objet prospect est créé si l'id n'existe pas sinon un message et
	 *         une erreur 409
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Prospect prospect) {
		Optional<Prospect> p = prospectRepository.findById(prospect.getId());
		if (p.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce type de projet existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(prospectRepository.save(prospect));
		}
	}

	/**
	 * Modifier un prospect
	 * 
	 * @param L'objet prospect dans le body de la requête
	 * @return L'objet prospect modifié ou une erreur 404 et un message s'il n'a pas
	 *         été trouvé en base de données
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Prospect prospect) {
		Optional<Prospect> p = prospectRepository.findById(prospect.getId());
		if (p.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body((prospectRepository.save(prospect)));
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Supprimer un prospect par son id
	 * 
	 * @param L'id du prospect à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProspect(@RequestParam int id) {
		prospectRepository.deleteById(id);
		return ResponseEntity.ok("Prospect supprimé");
	}
}
