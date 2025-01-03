package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateFacture;
    
    @Enumerated(EnumType.STRING) // Stocké comme texte
    private EtatFacture etat;
    
    private String referenceComptable;
    
    private BigDecimal montantTotalPayé = BigDecimal.ZERO;
    
    private BigDecimal montantTotal = BigDecimal.ZERO;

    @OneToOne
    private Commande commande;
    
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    public BigDecimal calculerSoldeRestant() {
        return montantTotal.subtract(montantTotalPayé);
    }
}
