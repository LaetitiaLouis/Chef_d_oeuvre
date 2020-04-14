package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Photo;
import fr.laetitia.model.Projet;
import fr.laetitia.repository.PhotoRepository;
import fr.laetitia.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author LOUISL
 *
 */
@RestController
@RequestMapping("/photo")
@CrossOrigin("http://localhost:4200")
public class PhotoController {

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	ProjetRepository projetRepository;

	/**
	 * Afficher toutes les photos
	 * 
	 * @return la liste de photos si elle n'est pas vide sinon une erreur 404
	 */

	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		List<Photo> photos = (List<Photo>) photoRepository.findAll();
		if (photos.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(photos);
		}
	}

	/**
	 * Enregistrer un objet photo
	 * 
	 * @param l'objet photo dans le body de la requête
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
	public ResponseEntity<?> findByProjet(@RequestParam int projet) {
		Optional<Projet> p = projetRepository.findById(projet);
		if (p.isPresent()) {
			Set<Photo> photos = p.get().getPhotos();
			if (photos.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
			return ResponseEntity.ok(photos);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Photo> photo = photoRepository.findById(id);
		if (photo.isPresent()) {
			return ResponseEntity.ok(photo.get());
		}else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	@GetMapping("/findByCategorie")
	public ResponseEntity<?> findByCategorie(@RequestParam String categorie) {
		Set<Photo> photos = photoRepository.findByCategorie(categorie);
		if (photos.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(photos);
		}
	}
	
}
