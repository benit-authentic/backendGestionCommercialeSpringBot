package cmdb.backend.gestioncommerciale.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {
    private Long id;
    private String nom;
    private int quantiteAlert;
    private int quantite;
    private double prixUnitaire;
    private Long tvaId;        // ID de la TVA associée
    private Long categorieId;  // ID de la catégorie associée
}
