package cmdb.backend.gestioncommerciale.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME, // Identifie le type par un champ
	    include = JsonTypeInfo.As.PROPERTY, // Inclut l'information dans une propriété JSON
	    property = "type" // Nom du champ contenant le type
	)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = Client.class, name = "CLIENT"),
	    @JsonSubTypes.Type(value = Fournisseur.class, name = "FOURNISSEUR")
	})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_PERSONNE", discriminatorType = DiscriminatorType.STRING)
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @Email(message = "Adresse email invalide")
    @Column(unique = true, nullable = false)
    private String email;

    private String telephone;

    private String adresse;

    @Column(unique = true)
    private String numCompte;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Commande> commandes = new ArrayList<>();
    
    @Transient // Empêche Hibernate de considérer ce champ comme persistant
    public String getType() {
        return this.getClass().getSimpleName().toUpperCase(); // Renvoie le type basé sur la classe
    }

}
