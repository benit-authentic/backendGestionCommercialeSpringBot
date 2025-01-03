package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.FicheStock;
import cmdb.backend.gestioncommerciale.entities.Personne;
import cmdb.backend.gestioncommerciale.entities.Produit;
import cmdb.backend.gestioncommerciale.entities.TypeMouvement;
import cmdb.backend.gestioncommerciale.repositories.FicheStockRepository;
import cmdb.backend.gestioncommerciale.services.FicheStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class FicheStockServiceImpl implements FicheStockService {

    private final FicheStockRepository ficheStockRepository;

    @Override
    public void deleteById(Long id) {
        if (!ficheStockRepository.existsById(id)) {
            throw new IllegalArgumentException("FicheStock introuvable avec l'ID : " + id);
        }
        ficheStockRepository.deleteById(id);
    }

    @Override
    public FicheStock findById(Long id) {
        return ficheStockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("FicheStock introuvable avec l'ID : " + id));
    }

    @Override
    public List<FicheStock> findAll() {
        return ficheStockRepository.findAll();
    }

    @Override
    public List<FicheStock> findByProduitId(Long produitId) {
        return ficheStockRepository.findByProduitId(produitId);
    }

    @Override
    public List<FicheStock> findByDateMouvement(String date) {
        try {
            return ficheStockRepository.findByDateMouvement(LocalDate.parse(date));
        } catch (Exception e) {
            throw new IllegalArgumentException("Date invalide : " + date, e);
        }
    }

    @Override
    public List<FicheStock> findByTypeMouvement(TypeMouvement typeMouvement) {
        if (typeMouvement == null) {
            throw new IllegalArgumentException("Le type de mouvement ne peut pas être null.");
        }
        return ficheStockRepository.findByTypeMouvement(typeMouvement);
    }
    
    @Override
    public void verifierEtMettreAJourStock(Produit produit, int quantite, TypeMouvement typeMouvement) {
        if (typeMouvement == TypeMouvement.SORTIE && produit.getQuantite() < quantite) {
            throw new IllegalArgumentException("Stock insuffisant pour le produit : " + produit.getNom());
        }

        if (typeMouvement == TypeMouvement.ENTREE) {
            produit.setQuantite(produit.getQuantite() + quantite);
        } else if (typeMouvement == TypeMouvement.SORTIE) {
            produit.setQuantite(produit.getQuantite() - quantite);
        }

        // Vérification seuil critique ou autre logique supplémentaire
        checkStockSeuilCritique(produit);
    }

    @Transactional
    @Override
    public void enregistrerFicheStock(Produit produit, int quantite, TypeMouvement typeMouvement, Personne personne) {
        validateInputs(produit, quantite, typeMouvement, personne);

        // Mise à jour du stock
        verifierEtMettreAJourStock(produit, quantite, typeMouvement);

        // Enregistrement du mouvement
        FicheStock ficheStock = new FicheStock();
        ficheStock.setProduit(produit);
        ficheStock.setQuantite(quantite);
        ficheStock.setTypeMouvement(typeMouvement);
        ficheStock.setDateMouvement(LocalDate.now());
        ficheStock.setPersonne(personne);
        ficheStockRepository.save(ficheStock);
    }


    /**
     * Valide les données avant d'enregistrer une fiche de stock.
     */
    private void validateInputs(Produit produit, int quantite, TypeMouvement typeMouvement, Personne personne) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être null.");
        }

        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro.");
        }

        if (typeMouvement == null) {
            throw new IllegalArgumentException("Le type de mouvement est obligatoire.");
        }

        if (personne == null) {
            throw new IllegalArgumentException("La personne associée au mouvement est obligatoire.");
        }
    }
    
    @Override
    public List<FicheStock> getHistoriqueProduit(Long produitId) {
        return ficheStockRepository.findByProduitIdOrderByDateMouvementDesc(produitId);
    }
    
    private void checkStockSeuilCritique(Produit produit) {
        int seuilCritique = produit.getQuantiteAlert();
        if (produit.getQuantite() <= seuilCritique) {
            System.out.println("Attention : le stock du produit " + produit.getNom() +
                    " a atteint un niveau critique (" + produit.getQuantite() + " unités restantes).");
        }
    }


}