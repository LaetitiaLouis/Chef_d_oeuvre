package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Admin;
import fr.laetitia.model.JsonWebToken;
import fr.laetitia.repository.AdminRepository;
import fr.laetitia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author LOUISL
 */

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Afficher tous les administrateurs
     */
    @GetMapping
    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
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
            if (admin.getPassword() == null) {
                admin.setPassword(maybeAdmin.get().getPassword());
            } else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body((adminRepository.save(admin)));
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Ajouter un admin dans la BDD
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Admin> signup(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(admin));
    }


    /**
     * Connecter un administrateur
     */
    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebToken> signin(@RequestBody Admin admin) {
        return ResponseEntity.ok(new JsonWebToken(userService.signin(admin.getLogin(), admin.getPassword())));
    }

    /**
     * Afficher un admin via son login
     */
    @GetMapping("/{login}")
    public ResponseEntity<?> findByLogin(@PathVariable String login) {
        Optional<Admin> admin = adminRepository.findByLogin(login);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return HttpResponse.NOT_FOUND;
        }
    }

    /**
     * Ajouter un admin dans la BDD
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

}



