package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Commande;
import cmdb.backend.gestioncommerciale.entities.Facture;

import java.util.List;

public interface FactureService {
    Facture findFactureById(Long id);                                // Trouver une facture par ID
    List<Facture> findAllFactures();                                 // Lister toutes les factures
    void deleteFactureById(Long id);                                    // Supprimer une facture par ID
    List<Facture> findFacturesByEtat(String etat);                   // Rechercher par état (ex: "Payée", "En attente")
    Facture findFactureByCommandeId(Long commandeId);                // Trouver une facture par commande
    Facture genererFacturePourCommande(Commande commande);     // Générer une facture pour une commande
	Facture recalculerEtatFacture(Long factureId);
}
