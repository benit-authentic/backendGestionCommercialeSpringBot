package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Paiement;

import java.util.List;

public interface PaiementService {
    List<Paiement> getAllPaiements();                              // Obtenir tous les paiements
    List<Paiement> getPaiementsByFacture(Long factureId);          // Obtenir les paiements liés à une facture
    List<Paiement> getPaiementsByStatut(String statut);            // Obtenir les paiements par statut
    Paiement enregistrerPaiement(Paiement paiement, Long factureId); // Enregistrer un paiement pour une facture
    void annulerFacture(Long factureId);                              // Annuler une facture
}
