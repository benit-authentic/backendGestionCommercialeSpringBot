package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.FicheStock;
import cmdb.backend.gestioncommerciale.entities.Personne;
import cmdb.backend.gestioncommerciale.entities.Produit;
import cmdb.backend.gestioncommerciale.entities.TypeMouvement;

import java.util.List;

public interface FicheStockService {
    void deleteById(Long id);
    FicheStock findById(Long id);
    List<FicheStock> findAll();

    // Méthodes spécifiques
    List<FicheStock> findByProduitId(Long produitId);
    List<FicheStock> findByDateMouvement(String date);
    List<FicheStock> findByTypeMouvement(TypeMouvement typeMouvement);

	void enregistrerFicheStock(Produit produit, int quantite, TypeMouvement typeMouvement, Personne personne);
	List<FicheStock> getHistoriqueProduit(Long produitId);
	void verifierEtMettreAJourStock(Produit produit, int quantite, TypeMouvement typeMouvement);
}
