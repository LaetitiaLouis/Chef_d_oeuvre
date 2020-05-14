package fr.laetitia.controller;

import fr.laetitia.HttpResponse;
import fr.laetitia.model.Type;
import fr.laetitia.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/types")
//@CrossOrigin("http://localhost:4200")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    /**
     * Obtenir la liste de tous les types
     */
    @GetMapping
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
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Type type) {
        Optional<Type> maybeType = typeRepository.findById(type.getId());
        if (maybeType.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce type de projet existe déjà");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(typeRepository.save(type));
        }
    }

    /**
     * Modifier un type
     */
    @PutMapping
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
     */
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteType(@PathVariable int id) {
        typeRepository.deleteById(id);
        return ResponseEntity.ok("Type supprimé");
    }

}
