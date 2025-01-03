package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.FicheStock;
import cmdb.backend.gestioncommerciale.entities.TypeMouvement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FicheStockRepository extends JpaRepository<FicheStock, Long> {
 // Recherche par produit
    List<FicheStock> findByProduitId(Long produitId);

    // Recherche par date
    List<FicheStock> findByDateMouvement(LocalDate dateMouvement);

    // Recherche par type de mouvement (ENTREE ou SORTIE)
    List<FicheStock> findByTypeMouvement(TypeMouvement typeMouvement);

	List<FicheStock> findByProduitIdOrderByDateMouvementDesc(Long produitId);
}
