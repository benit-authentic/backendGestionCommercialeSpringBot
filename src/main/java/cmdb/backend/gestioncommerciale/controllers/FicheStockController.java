package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.FicheStock;
import cmdb.backend.gestioncommerciale.entities.TypeMouvement;
import cmdb.backend.gestioncommerciale.services.FicheStockService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fiches-stock")
@RequiredArgsConstructor
public class FicheStockController {

    private final FicheStockService ficheStockService;

    // Récupérer une fiche de stock par ID
    @GetMapping("/{id}")
    public ResponseEntity<FicheStock> getFicheStockById(@PathVariable Long id) {
        FicheStock ficheStock = ficheStockService.findById(id);
        return ResponseEntity.ok(ficheStock);
    }

    // Supprimer une fiche de stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheStock(@PathVariable Long id) {
        ficheStockService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les fiches de stock
    @GetMapping
    public ResponseEntity<List<FicheStock>> getAllFichesStock() {
        List<FicheStock> fichesStock = ficheStockService.findAll();
        return ResponseEntity.ok(fichesStock);
    }

    // Récupérer les fiches de stock par produit
    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<FicheStock>> getFichesStockByProduit(@PathVariable Long produitId) {
        List<FicheStock> fichesStock = ficheStockService.findByProduitId(produitId);
        return ResponseEntity.ok(fichesStock);
    }

    // Récupérer les fiches de stock par type de mouvement
    @GetMapping("/mouvement")
    public ResponseEntity<List<FicheStock>> getFichesStockByTypeMouvement(@RequestParam TypeMouvement typeMouvement) {
        List<FicheStock> fichesStock = ficheStockService.findByTypeMouvement(typeMouvement);
        return ResponseEntity.ok(fichesStock);
    }

    // Récupérer les fiches de stock par date
    @GetMapping("/date")
    public ResponseEntity<List<FicheStock>> getFichesStockByDate(@RequestParam String date) {
        List<FicheStock> fichesStock = ficheStockService.findByDateMouvement(date);
        return ResponseEntity.ok(fichesStock);
    }
}
