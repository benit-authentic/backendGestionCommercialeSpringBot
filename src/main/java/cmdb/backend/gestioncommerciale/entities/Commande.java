package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCommande;
    
    @Enumerated(EnumType.STRING) // Stocké comme texte dans la base de données
    private TypeCommande typeCommande;
    
    @Enumerated(EnumType.STRING) // Stocké comme texte
    private EtatCommande etat;

    @OneToOne
    private Facture facture;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

    @JsonManagedReference // Gère la sérialisation des lignes de commande
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;
    
    public void addLigneCommande(LigneCommande ligne) {
        ligneCommandes.add(ligne);
        ligne.setCommande(this); // Liens bidirectionnels
    }
}
