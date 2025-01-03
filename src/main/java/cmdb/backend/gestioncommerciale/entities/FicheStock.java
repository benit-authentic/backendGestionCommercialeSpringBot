package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateMouvement;

    @Enumerated(EnumType.STRING)
    private TypeMouvement typeMouvement;
    
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
    
    @ManyToOne
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

}