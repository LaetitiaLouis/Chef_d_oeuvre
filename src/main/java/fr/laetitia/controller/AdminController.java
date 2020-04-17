package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Admin;
import fr.laetitia.model.JsonWebToken;
import fr.laetitia.repository.AdminRepository;
import fr.laetitia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author LOUISL
 */

@RestController
@RequestMapping("/admins")
@CrossOrigin("http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    /**
     * Affiche tous les administrateurs
     */
    @GetMapping
    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
    }

    /**
     * Enregistrer un nouvel administrateur
     */
    @PostMapping
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
     */
    @GetMapping("/loginExists")
    public boolean checkIfLoginExists(@RequestParam String login) {
        return adminRepository.existsById(login);
    }

    /**
     * Modifier un administrateur
     */
    @PutMapping
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
     */
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String login) {
        adminRepository.deleteById(login);
        return ResponseEntity.ok("Administrateur supprimé");
    }

    /**
     * ajoute un admin dans la BDD
     * @param admin
     * @return
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Admin> signup(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(admin));
    }

	/**
     * connecte un administrateur
     *
     */
	@PostMapping("/sign-in")
	public ResponseEntity<JsonWebToken> signin(@RequestBody Admin admin) {
		return ResponseEntity.ok(new JsonWebToken(userService.signin(admin.getLogin(), admin.getPassword())));


//
//	    Optional<Admin> admin = adminRepository.findByLogin(administrateur.getLogin());
//		if (admin.isPresent()) {
//			if (administrateur.getPassword().equals(admin.get().getPassword())) {
//				return ResponseEntity.ok(admin.get());
//			} else {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accès refusé");
//			}
//		} else {
//			return HttpResponse.NOT_FOUND;
		}
	}



