package cmdb.backend.gestioncommerciale.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal prixUnitaire;
    private int quantite;
    
    @Transient // Pas persisté dans la base de données
    public BigDecimal getTotal() {
        return prixUnitaire.multiply(BigDecimal.valueOf(quantite));
    }


    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @JsonBackReference // Évite les références circulaires avec Commande
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
}
