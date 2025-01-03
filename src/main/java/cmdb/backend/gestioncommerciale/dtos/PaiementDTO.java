package cmdb.backend.gestioncommerciale.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDTO {
    private Long id;
    private LocalDate datePaiement;
    private double montant;
    private String referenceComptable;
    private String statut;
    private Long factureId; // Référence à la facture associée
    private String typePaiement; // "Carte", "Chèque", "Espèce"
}
