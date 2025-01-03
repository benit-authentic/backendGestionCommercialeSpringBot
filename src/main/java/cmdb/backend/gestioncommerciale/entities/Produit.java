package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDateTime dateAjout;
    private int quantiteAlert;
    
    private int quantite;
    
    private BigDecimal prixUnitaire;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tva_id")
    private Tva tva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FicheStock> fichesStock;
}
