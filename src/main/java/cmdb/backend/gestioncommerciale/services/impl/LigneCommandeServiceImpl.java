package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.LigneCommande;
import cmdb.backend.gestioncommerciale.entities.Produit;
import cmdb.backend.gestioncommerciale.repositories.LigneCommandeRepository;
import cmdb.backend.gestioncommerciale.repositories.ProduitRepository;
import cmdb.backend.gestioncommerciale.services.LigneCommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final ProduitRepository produitRepository;

    @Override
    public LigneCommande createLigneCommande(LigneCommande ligneCommande) {
        validateLigneCommande(ligneCommande);

        Produit produit = produitRepository.findById(ligneCommande.getProduit().getId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'ID : " + ligneCommande.getProduit().getId()));

        ligneCommande.setProduit(produit);
        ligneCommande.setPrixUnitaire(produit.getPrixUnitaire());

        return ligneCommandeRepository.save(ligneCommande);
    }

    @Override
    public LigneCommande updateLigneCommande(Long id, LigneCommande ligneCommande) {
        LigneCommande existingLigne = findLigneCommandeById(id);

        validateLigneCommande(ligneCommande);

        existingLigne.setQuantite(ligneCommande.getQuantite());
        existingLigne.setPrixUnitaire(ligneCommande.getProduit().getPrixUnitaire());
        return ligneCommandeRepository.save(existingLigne);
    }

    @Override
    public LigneCommande findLigneCommandeById(Long id) {
        return ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("LigneCommande introuvable avec l'ID : " + id));
    }

    @Override
    public List<LigneCommande> findAllLignesCommande() {
        return ligneCommandeRepository.findAll();
    }

    @Override
    public void deleteLigneCommandeById(Long id) {
        if (!ligneCommandeRepository.existsById(id)) {
            throw new IllegalArgumentException("LigneCommande introuvable avec l'ID : " + id);
        }
        ligneCommandeRepository.deleteById(id);
    }

    @Override
    public List<LigneCommande> findLignesByCommandeId(Long commandeId) {
        return ligneCommandeRepository.findByCommandeId(commandeId);
    }

    /**
     * Valide les informations de la ligne de commande, y compris la disponibilité du stock.
     */
    private void validateLigneCommande(LigneCommande ligneCommande) {
        if (ligneCommande.getProduit() == null) {
            throw new IllegalArgumentException("Le produit associé à la ligne de commande est obligatoire.");
        }

        if (ligneCommande.getQuantite() <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro.");
        }
    }
}
