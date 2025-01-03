package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.Commande;
import cmdb.backend.gestioncommerciale.services.CommandeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    // Créer une commande
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande createdCommande = commandeService.createCommande(commande);
        return ResponseEntity.ok(createdCommande);
    }

    // Mettre à jour une commande
    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        Commande updatedCommande = commandeService.updateCommande(id, commande);
        return ResponseEntity.ok(updatedCommande);
    }

    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Commande commande = commandeService.findCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.findAllCommandes();
        return ResponseEntity.ok(commandes);
    }

    // Rechercher des commandes par personne
    @GetMapping("/personne/{personneId}")
    public ResponseEntity<List<Commande>> getCommandesByPersonne(@PathVariable Long personneId) {
        List<Commande> commandes = commandeService.findCommandesByPersonne(personneId);
        return ResponseEntity.ok(commandes);
    }

    // Calculer le total d'une commande
    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getCommandeTotal(@PathVariable Long id) {
        BigDecimal total = commandeService.calculerTotalCommande(id);
        return ResponseEntity.ok(total);
    }

    // Rechercher des commandes par état
    @GetMapping("/etat")
    public ResponseEntity<List<Commande>> getCommandesByEtat(@RequestParam String etat) {
        List<Commande> commandes = commandeService.findCommandesByEtat(etat);
        return ResponseEntity.ok(commandes);
    }
}
