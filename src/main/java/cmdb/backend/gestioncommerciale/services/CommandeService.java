package cmdb.backend.gestioncommerciale.services;


import java.math.BigDecimal;
import java.util.List;

import cmdb.backend.gestioncommerciale.dtos.CommandeCreateDTO;
import cmdb.backend.gestioncommerciale.entities.Commande;
import cmdb.backend.gestioncommerciale.entities.EtatCommande;

public interface CommandeService {
    Commande createCommande(CommandeCreateDTO commandeDTO);        // Ajouter une commande depuis DTO
    Commande createCommande(Commande commande);                    // Ajouter une commande
    Commande updateCommande(Long id, Commande commande);           // Mettre à jour une commande
    Commande findCommandeById(Long id);                            // Trouver une commande par ID
    List<Commande> findAllCommandes();                             // Lister toutes les commandes
    void deleteCommandeById(Long id);                              // Supprimer une commande par ID
    List<Commande> findCommandesByPersonne(Long personneId);       // Rechercher les commandes d'une personne
    List<Commande> findCommandesByEtat(String etat);               // Rechercher par état (ex : "En cours", "Livrée")
	void annulerCommande(Long commandeId);
	void mettreAJourEtatCommande(Long commandeId, EtatCommande nouvelEtat);
	void processLigneCommandes(Commande commande);
	BigDecimal calculerTotalCommande(Long commandeId);
}
