package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.*;
import cmdb.backend.gestioncommerciale.repositories.CommandeRepository;
import cmdb.backend.gestioncommerciale.repositories.PersonneRepository;
import cmdb.backend.gestioncommerciale.repositories.ProduitRepository;
import cmdb.backend.gestioncommerciale.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final FicheStockServiceImpl ficheStockService;
    private final ProduitRepository produitRepository;
    private final PersonneRepository<Personne> personneRepository;

    @Transactional
    @Override
    public Commande createCommande(Commande commande) {
        validateCommandeType(commande);

        // Vérifie que les lignes de commande sont valides
        if (commande.getLigneCommandes() == null || commande.getLigneCommandes().isEmpty()) {
            throw new IllegalArgumentException("Une commande doit contenir au moins une ligne.");
        }
        // Assurer que la date de commande est définie
        commande.setDateCommande(commande.getDateCommande() != null ? commande.getDateCommande() : LocalDateTime.now());

        // Gérer les commandes en fonction du type
        if (commande.getTypeCommande() == TypeCommande.APPROVISIONNEMENT) {
            commande.setEtat(EtatCommande.TERMINÉE);
            processLigneCommandes(commande); // Traiter automatiquement les mouvements de stock
        } else {
            commande.setEtat(EtatCommande.EN_ATTENTE); // Les commandes de type VENTE restent en attente
        }

        // Associer chaque ligne de commande à la commande parente
        for (LigneCommande ligne : commande.getLigneCommandes()) {
            ligne.setCommande(commande);
        }

        // Sauvegarder la commande avec les lignes associées
        return commandeRepository.save(commande);
    }



    @Override
    public Commande updateCommande(Long id, Commande commande) {
        Commande existingCommande = findCommandeById(id);

        existingCommande.setEtat(commande.getEtat());
        existingCommande.setPersonne(commande.getPersonne());

        return commandeRepository.save(existingCommande);
    }

    @Override
    public Commande findCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable avec l'ID : " + id));
    }

    @Override
    @Transactional
    public void mettreAJourEtatCommande(Long commandeId, EtatCommande nouvelEtat) {
        Commande commande = findCommandeById(commandeId);

        // Vérifier que la commande est de type VENTE
        if (commande.getTypeCommande() != TypeCommande.VENTE) {
            throw new IllegalStateException("Seules les commandes de type VENTE peuvent être mises à jour via une facture.");
        }

        // Mettre à jour l'état de la commande
        commande.setEtat(nouvelEtat);
        commandeRepository.save(commande);
    }
    
    @Override
    @Transactional
    public void processLigneCommandes(Commande commande) {
    	for (LigneCommande ligneCommande : commande.getLigneCommandes()) {
    	    Produit produit = produitRepository.findById(ligneCommande.getProduit().getId())
    	            .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'ID : " + ligneCommande.getProduit().getId()));

    	    ligneCommande.setPrixUnitaire(produit.getPrixUnitaire());
    	    
            int quantite = ligneCommande.getQuantite();
            TypeMouvement typeMouvement = commande.getTypeCommande() == TypeCommande.VENTE 
                ? TypeMouvement.SORTIE 
                : TypeMouvement.ENTREE;

            ficheStockService.enregistrerFicheStock(produit, quantite, typeMouvement, commande.getPersonne());
            produitRepository.save(produit);
        }
    }

    private void validateCommandeType(Commande commande) {
    	// Trouver la personne associée à la commande (Client ou Fournisseur)
        Personne personne = personneRepository.findById(commande.getPersonne().getId())
            .orElseThrow(() -> new RuntimeException("Personne introuvable"));
        
        if (commande.getTypeCommande() == null) {
            throw new IllegalArgumentException("Le type de commande est obligatoire.");
        }

        if (commande.getTypeCommande() == TypeCommande.VENTE && !(commande.getPersonne() instanceof Client)) {
            throw new IllegalArgumentException("Une commande de type VENTE doit être associée à un client.");
        }

        if (commande.getTypeCommande() == TypeCommande.APPROVISIONNEMENT && !(commande.getPersonne() instanceof Fournisseur)) {
            throw new IllegalArgumentException("Une commande de type APPROVISIONNEMENT doit être associée à un fournisseur.");
        }
     // Associer la personne à la commande
        commande.setPersonne(personne);
    }

    @Override
    public void annulerCommande(Long commandeId) {
        // Trouver la commande par ID
        Commande commande = findCommandeById(commandeId);

        // Vérifier si la commande peut être annulée
        if (commande.getEtat() != EtatCommande.EN_ATTENTE) {
            throw new IllegalStateException("Seules les commandes en attente peuvent être annulées.");
        }

        // Mettre à jour l'état de la commande
        commande.setEtat(EtatCommande.ANNULÉE);
        commandeRepository.save(commande);
    }
    
    @Override
    @Transactional
    public BigDecimal calculerTotalCommande(Long commandeId) {
        Commande commande = findCommandeById(commandeId);
        return commande.getLigneCommandes().stream()
                .map(ligne -> ligne.getPrixUnitaire().multiply(BigDecimal.valueOf(ligne.getQuantite())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



    @Override
    public List<Commande> findAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public void deleteCommandeById(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new IllegalArgumentException("Commande introuvable avec l'ID : " + id);
        }
        commandeRepository.deleteById(id);
    }

    @Override
    public List<Commande> findCommandesByPersonne(Long personneId) {
        return commandeRepository.findByPersonneId(personneId);
    }

    @Override
    public List<Commande> findCommandesByEtat(String etat) {
        return commandeRepository.findByEtat(etat);
    }
}
