package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.Categorie;
import cmdb.backend.gestioncommerciale.services.CategorieService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    // Créer une nouvelle catégorie
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie createdCategorie = categorieService.createCategorie(categorie);
        return ResponseEntity.ok(createdCategorie);
    }

    // Mettre à jour une catégorie existante
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        Categorie updatedCategorie = categorieService.updateCategorie(id, categorie);
        return ResponseEntity.ok(updatedCategorie);
    }

    // Trouver une catégorie par ID
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Categorie categorie = categorieService.findCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    // Récupérer toutes les catégories
    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Supprimer une catégorie par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorieById(id);
        return ResponseEntity.noContent().build();
    }

    // Rechercher des catégories par nom
    @GetMapping("/search")
    public ResponseEntity<List<Categorie>> searchCategoriesByNom(@RequestParam String nom) {
        List<Categorie> categories = categorieService.findCategoriesByNom(nom);
        return ResponseEntity.ok(categories);
    }
}
