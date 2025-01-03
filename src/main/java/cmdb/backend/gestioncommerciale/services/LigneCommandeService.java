package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.LigneCommande;

import java.util.List;

public interface LigneCommandeService {
    LigneCommande createLigneCommande(LigneCommande ligneCommande);      // Ajouter une ligne de commande
    LigneCommande updateLigneCommande(Long id, LigneCommande ligneCommande); // Mettre à jour une ligne de commande
    LigneCommande findLigneCommandeById(Long id);                              // Trouver une ligne de commande par ID
    List<LigneCommande> findAllLignesCommande();                               // Lister toutes les lignes de commande
    void deleteLigneCommandeById(Long id);                                        // Supprimer une ligne de commande
    List<LigneCommande> findLignesByCommandeId(Long commandeId);               // Rechercher les lignes par commande
}
