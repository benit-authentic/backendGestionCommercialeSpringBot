package cmdb.backend.gestioncommerciale.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeResponseDTO {
	private Long id;
    private String typeCommande;   
    private Long personneId;
    private String personneType;
    private LocalDateTime dateCommande; 
    private String etat;
    private List<LigneCommandeResponseDTO> ligneCommandes;
    
 // DTO pour une ligne de commande dans la réponse
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LigneCommandeResponseDTO {
        private Long id;             // ID de la ligne de commande
        private Long produitId;      // ID du produit
        private String produitNom;   // Nom du produit
        private int quantite;        // Quantité commandée
        private BigDecimal prixUnitaire; // Prix unitaire du produit
        private BigDecimal total;        // Total calculé (prixUnitaire * quantite)
    }

}
