package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.*;
import cmdb.backend.gestioncommerciale.services.PaiementService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    // Enregistrer un paiement en espèces
    @PostMapping
    public ResponseEntity<PaiementEspece> enregistrerPaiementEspece(
            @RequestBody PaiementEspece paiementEspece,
            @RequestParam Long factureId) {
        PaiementEspece savedPaiement = (PaiementEspece) paiementService.enregistrerPaiement(paiementEspece, factureId);
        return ResponseEntity.ok(savedPaiement);
    }

    // Enregistrer un paiement spécifique (ex. : PaiementCarte)
    @PostMapping("/carte")
    public ResponseEntity<PaiementCarte> enregistrerPaiementCarte(
            @RequestBody PaiementCarte paiementCarte,
            @RequestParam Long factureId) {
        PaiementCarte savedPaiement = (PaiementCarte) paiementService.enregistrerPaiement(paiementCarte, factureId);
        return ResponseEntity.ok(savedPaiement);
    }

    // Enregistrer un paiement spécifique (ex. : PaiementCheque)
    @PostMapping("/cheque")
    public ResponseEntity<PaiementCheque> enregistrerPaiementCheque(
            @RequestBody PaiementCheque paiementCheque,
            @RequestParam Long factureId) {
        PaiementCheque savedPaiement = (PaiementCheque) paiementService.enregistrerPaiement(paiementCheque, factureId);
        return ResponseEntity.ok(savedPaiement);
    }

    // Récupérer les paiements d'une facture
    @GetMapping("/facture/{factureId}")
    public ResponseEntity<List<Paiement>> getPaiementsByFacture(@PathVariable Long factureId) {
        List<Paiement> paiements = paiementService.getPaiementsByFacture(factureId);
        return ResponseEntity.ok(paiements);
    }

    // Récupérer tous les paiements ou filtrer par statut
    @GetMapping
    public ResponseEntity<List<Paiement>> getPaiements(
            @RequestParam(required = false) String statut) {
        List<Paiement> paiements;
        if (statut != null && !statut.isEmpty()) {
            paiements = paiementService.getPaiementsByStatut(statut);
        } else {
            paiements = paiementService.getAllPaiements();
        }
        return ResponseEntity.ok(paiements);
    }

    // Annuler une facture (et ses paiements)
    @PutMapping("/annuler/{factureId}")
    public ResponseEntity<Void> annulerFacture(@PathVariable Long factureId) {
        paiementService.annulerFacture(factureId);
        return ResponseEntity.noContent().build();
    }
}
