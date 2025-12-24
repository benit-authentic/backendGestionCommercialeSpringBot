package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.Commande;
import cmdb.backend.gestioncommerciale.entities.EtatCommande;
import cmdb.backend.gestioncommerciale.entities.Facture;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	Optional<Commande> findByFacture(Facture facture);
	List<Commande> findByPersonneId(Long personneId);    // Rechercher les commandes par personne
    List<Commande> findByEtat(EtatCommande etat);        // Rechercher par état (enum)
}
