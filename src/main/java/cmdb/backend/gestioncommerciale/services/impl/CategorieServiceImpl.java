package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.Categorie;
import cmdb.backend.gestioncommerciale.repositories.CategorieRepository;
import cmdb.backend.gestioncommerciale.services.CategorieService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    @Override
    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Long id, Categorie categorie) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));
        existingCategorie.setNom(categorie.getNom());
        existingCategorie.setDescription(categorie.getDescription());
        return categorieRepository.save(existingCategorie);
    }

    @Override
    public Categorie findCategorieById(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public void deleteCategorieById(Long id) {
        if (!categorieRepository.existsById(id)) {
            throw new RuntimeException("Categorie not found with id: " + id);
        }
        categorieRepository.deleteById(id);
    }

    @Override
    public List<Categorie> findCategoriesByNom(String nom) {
        return categorieRepository.findByNomContainingIgnoreCase(nom);
    }
}