package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.Produit;
import cmdb.backend.gestioncommerciale.services.ProduitService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    // Créer un nouveau produit
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit createdProduit = produitService.createProduit(produit);
        return ResponseEntity.ok(createdProduit);
    }

    // Mettre à jour un produit existant
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        return ResponseEntity.ok(updatedProduit);
    }

    // Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.findProduitById(id);
        return ResponseEntity.ok(produit);
    }

    // Récupérer tous les produits
    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.findAllProduits();
        return ResponseEntity.ok(produits);
    }

    // Supprimer un produit par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduitById(id);
        return ResponseEntity.noContent().build();
    }

    // Rechercher des produits par catégorie
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<Produit>> getProduitsByCategorie(@PathVariable Long categorieId) {
        List<Produit> produits = produitService.findProduitsByCategorie(categorieId);
        return ResponseEntity.ok(produits);
    }

    // Rechercher des produits sous le seuil d'alerte de quantité
    @GetMapping("/alertes-quantite")
    public ResponseEntity<List<Produit>> getProduitsBelowQuantiteAlert(@RequestParam int quantiteAlert) {
        List<Produit> produits = produitService.findProduitsBelowQuantiteAlert(quantiteAlert);
        return ResponseEntity.ok(produits);
    }
}
