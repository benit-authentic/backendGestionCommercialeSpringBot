package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datePaiement;
    private BigDecimal montant;
    private String referenceComptable;
    private String statut;
    
    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;
}
