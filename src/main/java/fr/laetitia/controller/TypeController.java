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
import fr.laetitia.model.Type;
import fr.laetitia.repository.TypeRepository;

@RestController
@RequestMapping("/type")
@CrossOrigin("http://localhost:4200")
public class TypeController {

	@Autowired
	TypeRepository typeRepository;

	/**
	 * Obtenir la liste de tous les types
	 * 
	 * @return La liste si elle n'est pas vide sinon une erreur 404 et un message
	 */
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		Iterable<Type> types = typeRepository.findAll();
		if (types.iterator().hasNext()) {
			return ResponseEntity.ok(types);
		} else {
			return HttpResponse.NOT_FOUND;

		}
	}

	/**
	 * Enregistrer un objet type
	 * 
	 *
	 * @return L'objet type est créé s'il n'existe pas sinon un message et une
	 *         erreur 409
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Type type) {
		Optional<Type> maybeType = typeRepository.findById(type.getId());
		if (maybeType.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce type de projet existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(typeRepository.save(type));
		}
	}

	/**
	 * Modifier un type
	 * 
	 * @param L'objet type dans le body de la requête
	 * @return L'objet type modifié ou une erreur 404 et un message s'il n'a pas été
	 *         trouvé en base de données
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Type type) {
		Optional<Type> maybeType = typeRepository.findById(type.getId());
		if (maybeType.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body((typeRepository.save(type)));
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Supprimer un type par son id
	 * 
	 * @param L'id du type à supprimer
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteType(@RequestParam int id) {
		typeRepository.deleteById(id);
		return ResponseEntity.ok("Type supprimé");
	}

}
