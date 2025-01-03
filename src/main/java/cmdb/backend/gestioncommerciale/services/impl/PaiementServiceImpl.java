package cmdb.backend.gestioncommerciale.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmdb.backend.gestioncommerciale.entities.EtatFacture;
import cmdb.backend.gestioncommerciale.entities.Facture;
import cmdb.backend.gestioncommerciale.entities.Paiement;

import cmdb.backend.gestioncommerciale.repositories.FactureRepository;
import cmdb.backend.gestioncommerciale.repositories.PaiementRepository;
import cmdb.backend.gestioncommerciale.services.FactureService;
import cmdb.backend.gestioncommerciale.services.PaiementService;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final FactureRepository factureRepository;
    private final FactureService factureService;

    @Transactional
    @Override
    public Paiement enregistrerPaiement(Paiement paiement, Long factureId) {
        // Récupérer la facture associée
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new RuntimeException("Facture introuvable avec l'ID : " + factureId));

        paiement.setDatePaiement(paiement.getDatePaiement() != null ? paiement.getDatePaiement() : LocalDateTime.now());
        // Associer le paiement à la facture
        paiement.setFacture(facture);
        paiementRepository.save(paiement);

        // Recalculer l'état de la facture via FactureService
        factureService.recalculerEtatFacture(factureId);

        return paiement;
    }

    @Transactional
    @Override
    public void annulerFacture(Long factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new RuntimeException("Facture introuvable avec l'ID : " + factureId));

        // Annule les paiements associés
        facture.getPaiements().forEach(paiement -> paiement.setStatut("ANNULÉ"));
        paiementRepository.saveAll(facture.getPaiements());

        // Mettre à jour l'état de la facture
        facture.setEtat(EtatFacture.ANNULÉE);
        factureRepository.save(facture);
    }

    // Récupérer les paiements d'une facture
    @Override
    public List<Paiement> getPaiementsByFacture(Long factureId) {
        return paiementRepository.findByFactureId(factureId);
    }

    // Récupérer les paiements par statut
    @Override
    public List<Paiement> getPaiementsByStatut(String statut) {
        return paiementRepository.findByStatut(statut);
    }
}

