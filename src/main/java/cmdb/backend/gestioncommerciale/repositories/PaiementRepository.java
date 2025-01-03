package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.Paiement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    // Méthodes spécifiques si nécessaire
    List<Paiement> findByFactureId(Long factureId);
    List<Paiement> findByStatut(String statut);
    Paiement findByReferenceComptable(String referenceComptable);
}
