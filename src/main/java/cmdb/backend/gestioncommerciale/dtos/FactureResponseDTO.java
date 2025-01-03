package cmdb.backend.gestioncommerciale.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureResponseDTO {
    private Long id;
    private LocalDateTime dateFacture;
    private String referenceComptable;
    private BigDecimal montantTotal;
    private BigDecimal montantTotalPayé;
    private BigDecimal soldeRestant;
    private String etat; // Enum EtatFacture sous forme de chaîne de caractères
    private Long commandeId;
    private String typeCommande;
    private Long personneId;
    private String personneType;
    private List<CommandeResponseDTO.LigneCommandeResponseDTO> ligneCommandes;
}
