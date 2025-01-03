package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Categorie;

import java.util.List;

public interface CategorieService {
	Categorie createCategorie(Categorie categorie);
    Categorie updateCategorie(Long id, Categorie categorie);
    Categorie findCategorieById(Long id);
    List<Categorie> findAllCategories();
    void deleteCategorieById(Long id);
    List<Categorie> findCategoriesByNom(String nom);
}
