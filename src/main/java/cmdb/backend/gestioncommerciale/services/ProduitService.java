package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Produit;

import java.util.List;

public interface ProduitService {
    Produit createProduit(Produit produit);                          // Ajouter un produit
    Produit updateProduit(Long id, Produit produit);                // Mettre à jour un produit
    Produit findProduitById(Long id);                               // Trouver un produit par ID
    List<Produit> findAllProduits();                                // Lister tous les produits
    void deleteProduitById(Long id);                                // Supprimer un produit par ID
    List<Produit> findProduitsByCategorie(Long categorieId);        // Rechercher par catégorie
    List<Produit> findProduitsBelowQuantiteAlert(int quantiteAlert);                 // Produits sous le seuil d'alerte
}