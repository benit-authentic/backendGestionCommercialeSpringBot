package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

    // Pour authentification
    private String username;
    private String password;

    // Relation avec les rôles
    @ManyToMany(fetch = FetchType.EAGER) // Charger les rôles directement
    @JoinTable(
        name = "utilisateur_role", // Table d'association
        joinColumns = @JoinColumn(name = "utilisateur_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles; // Un utilisateur peut avoir plusieurs rôles
}
