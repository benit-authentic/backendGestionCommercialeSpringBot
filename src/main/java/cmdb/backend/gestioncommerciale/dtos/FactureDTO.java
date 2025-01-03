package cmdb.backend.gestioncommerciale.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cmdb.backend.gestioncommerciale.entities.EtatFacture;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureDTO {
    private Long id;
    private LocalDateTime dateFacture;
    private EtatFacture etat;
    private String referenceComptable;
    private BigDecimal montantTotal;
    private BigDecimal montantTotalPayé;
    private Long commandeId; // ID de la commande associée
}
