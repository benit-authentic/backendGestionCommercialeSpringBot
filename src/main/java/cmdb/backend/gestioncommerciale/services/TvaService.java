package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Tva;

import java.util.List;

public interface TvaService {
    Tva createTva(Tva tva);                           // Ajouter un taux de TVA
    Tva updateTva(Long id, Tva tva);                 // Mettre à jour un taux de TVA
    Tva findTvaById(Long id);                        // Trouver une TVA par ID
    List<Tva> findAllTvas();                         // Lister toutes les TVAs
    void deleteTvaById(Long id);                     // Supprimer une TVA
}
