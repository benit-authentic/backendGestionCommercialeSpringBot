package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.Produit;
import cmdb.backend.gestioncommerciale.repositories.ProduitRepository;
import cmdb.backend.gestioncommerciale.services.ProduitService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Override
    public Produit createProduit(Produit produit) {
        produit.setDateAjout(produit.getDateAjout() == null ? LocalDateTime.now() : produit.getDateAjout());
        produit.setQuantite(0); // Toujours initialisée à 0
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Long id, Produit produit) {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found with id: " + id));
        existingProduit.setNom(produit.getNom());
        existingProduit.setQuantiteAlert(produit.getQuantiteAlert());
        existingProduit.setPrixUnitaire(produit.getPrixUnitaire());
        existingProduit.setCategorie(produit.getCategorie());
        existingProduit.setTva(produit.getTva());
        return produitRepository.save(existingProduit);
    }

    @Override
    public Produit findProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found with id: " + id));
    }

    @Override
    public List<Produit> findAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public void deleteProduitById(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit not found with id: " + id);
        }
        produitRepository.deleteById(id);
    }

    @Override
    public List<Produit> findProduitsByCategorie(Long categorieId) {
        return produitRepository.findByCategorieId(categorieId);
    }

    @Override
    public List<Produit> findProduitsBelowQuantiteAlert(int quantiteAlert) {
        return produitRepository.findByQuantiteLessThan(quantiteAlert);
    }
}