package fr.laetitia.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import fr.laetitia.model.Photo;
import fr.laetitia.model.Prestation;
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
@RequestMapping("/projet")
@CrossOrigin("http://localhost:4200")
public class ProjetController {

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	PrestationRepository prestationRepository;

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	TypeRepository typeRepository;

	/**
	 * Obtenir la liste de tous les projets
	 * 
	 * @return La liste de tous les projets si elle n'est pas vide sinon un message
	 *         et une erreur 404
	 */
	@GetMapping("/")
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
	 * 
	 * @param 'objet projet modifié en paramètre de la requête
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
	 * 
	 *
	 * @return Le projet crée si il n'existe pas déjà sinon un message et une erreur
	 *         404
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

	/**
	 * Obtenir les projets par type
	 * 
	 * @param Le libellé du type
	 * @return Une liste de projets si elle n'est pas vide sinon un message et une
	 *         erreur 404
	 */
//	@GetMapping("/findByType")
//	public ResponseEntity<?> findByType(@RequestParam int type) {
//		Optional<Type> t = typeRepository.findById(type);
//		if (t.isPresent()) {
//			Set<Projet> projets = t.get().getProjets();
//			if (projets.isEmpty()) {
//				return HttpResponse.NOT_FOUND;
//			} else {
//				return ResponseEntity.ok(projets);
//			}
//		} else {
//			return HttpResponse.NOT_FOUND;
//		}
//	}

	/**
	 * Obtenir les projets par prestation
	 * 
	 * @param Le libellé du prestation
	 * @return Une liste de projets si elle n'est pas vide sinon un message et une
	 *         erreur 404
	 */
//	@GetMapping("/findByPrestation")
//	public ResponseEntity<?> findByPrestation(@RequestParam int prestation) {
//		Optional<Prestation> p = prestationRepository.findById(prestation);
//		if (p.isPresent()) {
//			Set<Projet> projets = p.get().getProjets();
//			if (projets.isEmpty()) {
//				return HttpResponse.NOT_FOUND;
//			} else {
//			return ResponseEntity.ok(projets);
//			}
//		} else {
//			return HttpResponse.NOT_FOUND;
//		}
//	}

	/**
	 * Supprimer un projet par son id
	 * 
	 * @param L'id du projet à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProjet(@RequestParam int id) {
		projetRepository.deleteById(id);
		return ResponseEntity.ok("Projet supprimé");
	}
	
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Projet> projet = projetRepository.findById(id);
		if (projet.isPresent()) {
			return ResponseEntity.ok(projet.get());
		}else {
			return HttpResponse.NOT_FOUND;
		}
	}
}
