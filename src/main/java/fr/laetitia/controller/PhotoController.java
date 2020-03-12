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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;
import fr.laetitia.repository.PhotoRepository;
import fr.laetitia.repository.ProjetRepository;

/**
 * @author LOUISL
 *
 */
@RestController
@RequestMapping("/api/photo")
@CrossOrigin("http://localhost:4200")
public class PhotoController {

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	ProjetRepository projetRepository;

	/**
	 * Enregistrer un objet photo
	 * 
	 * @param L'objet photo dans le body de la requête
	 * @return L'objet photo est créé si l'id n'existe pas sinon un message et une
	 *         erreur 409
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Photo photo) {
		Optional<Photo> maybePhoto = photoRepository.findById(photo.getId());
		if (maybePhoto.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cette photo existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(photoRepository.save(photo));
		}
	}

	/**
	 * Supprimer une photo par son id
	 * 
	 * @param L'id de la photo à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deletePhoto(@RequestParam int id) {
		photoRepository.deleteById(id);
		return ResponseEntity.ok("Photo supprimée");
	}

	/**
	 * Obtenir la liste des photos d'un projet
	 * 
	 * @param Le nom du projet en paramètre de la requête
	 * @return La liste des photos si elle n'est pas vide sinon un message et une
	 *         erreur 404
	 */
	@GetMapping("/findByProjet")
	public ResponseEntity<?> findByProjet(@RequestParam int id) {
		Optional<Projet> projet = projetRepository.findById(id);
		if (projet.isPresent()) {
			List<Photo> photos = projet.get().getPhotos();
			return ResponseEntity.ok(photos);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
}
