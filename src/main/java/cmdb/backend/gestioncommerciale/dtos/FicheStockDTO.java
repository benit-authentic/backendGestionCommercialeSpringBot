package cmdb.backend.gestioncommerciale.dtos;

import cmdb.backend.gestioncommerciale.entities.TypeMouvement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheStockDTO {
    private Long id;
    private LocalDate dateMouvement;
    private TypeMouvement typeMouvement;
    private int quantite;
    private Long produitId; // Simplifié pour éviter de transmettre tout l'objet Produit
    private String produitNom; // Nom du produit pour un affichage rapide
    private Long personneId; // Simplifié pour éviter de transmettre tout l'objet Personne
    private String personneNom; // Nom de la personne associée
}
