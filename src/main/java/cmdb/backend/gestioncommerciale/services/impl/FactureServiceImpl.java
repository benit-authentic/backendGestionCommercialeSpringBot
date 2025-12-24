package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.*;
import cmdb.backend.gestioncommerciale.repositories.FactureRepository;
import cmdb.backend.gestioncommerciale.services.CommandeService;
import cmdb.backend.gestioncommerciale.services.FactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final CommandeService commandeService;

    @Override
    @Transactional
    public Facture genererFacturePourCommande(Commande commande) {
        if (commande == null) {
            throw new IllegalArgumentException("La commande ne peut pas être null.");
        }

        // Vérifie que la commande est de type VENTE
        if (commande.getTypeCommande() != TypeCommande.VENTE) {
            throw new IllegalArgumentException("Seules les commandes de type VENTE peuvent générer une facture.");
        }
        
     // Calcul du montant total de la facture via la méthode centralisée
        BigDecimal montantTotal = commandeService.calculerTotalCommande(commande.getId());

        // Génère une référence unique pour la facture
        String referenceComptable = String.format("FAC-%d-%d", commande.getId(), LocalDate.now().getYear());

        // Crée une nouvelle facture
        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setDateFacture(LocalDateTime.now());
        facture.setEtat(EtatFacture.NON_PAYÉE); // Initialiser l'état de la facture
        facture.setReferenceComptable(referenceComptable);
        facture.setMontantTotal(montantTotal);
        // Persist la facture dans la base de données
        return factureRepository.save(facture);
    }

    @Override
    public Facture findFactureById(Long id) {
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Facture introuvable avec l'ID : " + id));

        // Accéder au client via la commande et afficher ses informations
        Personne client = facture.getCommande().getPersonne();
        if (client != null) {
            System.out.println("Client associé à la facture : " + client.getNom() + ", Email : " + client.getEmail());
        }

        return facture;
    }

    @Override
    @Transactional
    public Facture recalculerEtatFacture(Long factureId) {
        Facture facture = findFactureById(factureId);

        BigDecimal montantTotalPayé = facture.getPaiements().stream()
                .map(Paiement::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        facture.setMontantTotalPayé(montantTotalPayé);

        // Mise à jour de l'état de la facture
        if (montantTotalPayé.compareTo(facture.getMontantTotal()) >= 0) {
            facture.setEtat(EtatFacture.PAYÉE_TOTALEMENT);
        } else if (montantTotalPayé.compareTo(BigDecimal.ZERO) > 0) {
            facture.setEtat(EtatFacture.PAYÉE_PARTIELLEMENT);
        } else {
            facture.setEtat(EtatFacture.NON_PAYÉE);
        }

        // Mettre à jour l'état de la commande associée
        mettreAJourEtatCommande(facture);

        return factureRepository.save(facture);
    }

    /**
     * Met à jour l'état de la commande associée à une facture en fonction de l'état de la facture.
     * @param facture La facture dont l'état doit être synchronisé avec la commande.
     */
    private void mettreAJourEtatCommande(Facture facture) {
        Commande commande = facture.getCommande();

        if (facture.getEtat() == EtatFacture.PAYÉE_TOTALEMENT) {
            commandeService.mettreAJourEtatCommande(commande.getId(), EtatCommande.TERMINÉE);
            commandeService.processLigneCommandes(commande);
        } else if (facture.getEtat() == EtatFacture.PAYÉE_PARTIELLEMENT) {
            commandeService.mettreAJourEtatCommande(commande.getId(), EtatCommande.EN_ATTENTE);
        } else if (facture.getEtat() == EtatFacture.NON_PAYÉE) {
            commandeService.mettreAJourEtatCommande(commande.getId(), EtatCommande.EN_ATTENTE);
        }
    }

    @Override
    public List<Facture> findAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public void deleteFactureById(Long id) {
        if (!factureRepository.existsById(id)) {
            throw new IllegalArgumentException("Facture introuvable avec l'ID : " + id);
        }
        factureRepository.deleteById(id);
    }

    @Override
    public List<Facture> findFacturesByEtat(String etat) {
        if (etat == null || etat.isEmpty()) {
            throw new IllegalArgumentException("L'état de la facture ne peut pas être null ou vide.");
        }

        try {
            EtatFacture etatEnum = EtatFacture.valueOf(etat);
            return factureRepository.findByEtat(etatEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("État de facture invalide : " + etat + 
                ". Valeurs acceptées : NON_PAYÉE, PAYÉE_PARTIELLEMENT, PAYÉE_TOTALEMENT, ANNULÉE");
        }
    }

    @Override
    public Facture findFactureByCommandeId(Long commandeId) {
        if (commandeId == null) {
            throw new IllegalArgumentException("L'ID de la commande ne peut pas être null.");
        }

        return factureRepository.findByCommandeId(commandeId)
                .orElseThrow(() -> new IllegalArgumentException("Aucune facture trouvée pour la commande avec l'ID : " + commandeId));
    }
}
