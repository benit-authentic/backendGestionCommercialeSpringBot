package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.Commande;
import cmdb.backend.gestioncommerciale.entities.Facture;
import cmdb.backend.gestioncommerciale.services.CommandeService;
import cmdb.backend.gestioncommerciale.services.FactureService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureService factureService;
    private final CommandeService commandeService;

    // Générer une facture pour une commande
    @PostMapping("/generate/{commandeId}")
    public ResponseEntity<Facture> genererFacture(@PathVariable Long commandeId) {
        // Récupère la commande à partir de l'ID
        Commande commande = commandeService.findCommandeById(commandeId);

        // Génère une facture pour la commande
        Facture facture = factureService.genererFacturePourCommande(commande);

        // Retourne la facture générée
        return ResponseEntity.ok(facture);
    }


    // Récupérer une facture par ID
    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
        Facture facture = factureService.findFactureById(id);
        return ResponseEntity.ok(facture);
    }

    // Recalculer l'état d'une facture
    @PatchMapping("/{id}/recalculate")
    public ResponseEntity<Facture> recalculerEtatFacture(@PathVariable Long id) {
        Facture updatedFacture = factureService.recalculerEtatFacture(id);
        return ResponseEntity.ok(updatedFacture);
    }

    // Supprimer une facture
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFactureById(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les factures
    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.findAllFactures();
        return ResponseEntity.ok(factures);
    }

    // Rechercher des factures par état
    @GetMapping("/etat")
    public ResponseEntity<List<Facture>> getFacturesByEtat(@RequestParam String etat) {
        List<Facture> factures = factureService.findFacturesByEtat(etat);
        return ResponseEntity.ok(factures);
    }

    // Récupérer une facture par commande
    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<Facture> getFactureByCommandeId(@PathVariable Long commandeId) {
        Facture facture = factureService.findFactureByCommandeId(commandeId);
        return ResponseEntity.ok(facture);
    }
}
