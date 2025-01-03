package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Personne;

import java.util.List;
import java.util.Optional;

public interface PersonneService<T extends Personne> {
    T save(T personne);                                            // Sauvegarder une personne
    Optional<T> findById(Long id);                                    // Trouver une personne par ID
    List<T> findAll();                                                // Lister toutes les personnes
    void deleteById(Long id);                                         // Supprimer une personne par ID

    // Méthodes de recherche communes
    T findByEmail(String email);                                      // Trouver par email
    T findByNumCompte(String numCompte);                              // Trouver par numéro de compte
    List<T> findByNom(String nom);                                    // Trouver par nom
}
