package fr.laetitia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Projet;
import fr.laetitia.model.Type;
import fr.laetitia.repository.PhotoRepository;
import fr.laetitia.repository.PrestationRepository;
import fr.laetitia.repository.ProjetRepository;
import fr.laetitia.repository.TypeRepository;

/**
 * @author LOUISL
 *
 */
@RestController
@RequestMapping("/api/projet")
@CrossOrigin("http://localhost:4200")
public class ProjetController {

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	PrestationRepository presentationrepository;

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	TypeRepository typeRepository;

	/**
	 * Obtenir la liste de tous les projets
	 * @return La liste de tout les projets si elle n'est pas vide sinon un message et une erreur 404
	 */
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<Projet> projets = (List<Projet>) projetRepository.findAll();
		if (projets.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(projets);
		}
	}

	/**
	 * Modifier un projet
	 * @param L'objet projet modifié en paramètre de la requête
	 * @return Le projet modifié s'il existe sinon un message et une erreur 404
	 */
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@RequestBody Projet projet) {
		Optional<Projet> maybeProjet = projetRepository.findById(projet.getId());
		if (maybeProjet.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(projetRepository.save(projet));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce projet n'existe pas");
		}
	}

	/**
	 * Enregistrer un projet
	 * @param Le projet dans le body de la requête
	 * @return Le projet crée si il n'existe pas déjà sinon un message et une erreur 404       
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Projet projet) {
		Optional<Projet> maybeProjet = projetRepository.findById(projet.getId());
		if (maybeProjet.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce projet existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(projetRepository.save(projet));
		}
	}

//	/**
//	 * Obtenir les projets par type
//	 * @param Le libellé du type
//	 * @return Une liste de projets si elle n'est pas vide sinon un message et une erreur 404 
//	 */
//	@GetMapping("/findByType")
//	public ResponseEntity<?> findByType(@RequestParam int id) {
//		Optional<Type> t = typeRepository.findById(id);
//		if (t.isPresent()) {
//			List<Projet> projets = t.get().getProjets();
//			return ResponseEntity.ok(projets);
//		} else {
//			return HttpResponse.NOT_FOUND;
//		}
//	}
}
