package fr.laetitia.controller;

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
import fr.laetitia.model.Admin;
import fr.laetitia.repository.AdminRepository;

/**
 * @author LOUISL
 *
 */

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {

	@Autowired
	AdminRepository adminRepository;

	/**
	 * Enregistrer un nouvel administrateur
	 * 
	 * @param L'objet admin dans le body de la requête
	 * @return L'objet créé ou une erreur 409 si le login existe déjà
	 */
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Admin admin) {
		Optional<Admin> maybeAdmin = adminRepository.findById(admin.getLogin());
		if (maybeAdmin.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce login est déjà utilisé");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(adminRepository.save(admin));
		}
	}

	/**
	 * Vérifier si un administrateur avec le login proposé existe déjà
	 * 
	 * @param Le login d'un administrateur en paramètre de la requête
	 * @return true si il existe sinon false
	 */
	@GetMapping("/loginExists")
	public boolean checkIfLoginExists(@RequestParam String login) {
		return adminRepository.existsById(login);
	}

	/**
	 * Modifier un administrateur
	 * 
	 * @param L'objet admin dans le body de la requête
	 * @return L'objet admin modifié ou une erreur 404 et un message s'il n'a pas
	 *         été trouvé en base de donnée
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Admin admin) {
		Optional<Admin> maybeAdmin = adminRepository.findById(admin.getLogin());
		if (maybeAdmin.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body((adminRepository.save(admin)));
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Supprimer un administrateur par son id
	 * 
	 * @param L'id de l'administrateur à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAdmin(@RequestParam String login) {
		adminRepository.deleteById(login);
		return ResponseEntity.ok("Administrateur supprimé");
	}
}
