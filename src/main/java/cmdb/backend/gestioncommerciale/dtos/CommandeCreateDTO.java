package cmdb.backend.gestioncommerciale.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeCreateDTO {
    private String typeCommande; // VENTE ou APPROVISIONNEMENT
    private Long personneId; // Client ou Fournisseur
    private String typePersonne;
    private List<LigneCommandeCreateDTO> ligneCommandes;
    
 // DTO pour une ligne de commande à la création
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LigneCommandeCreateDTO {
        private Long produitId;      // ID du produit
        private int quantite;        // Quantité commandée
    }
}
