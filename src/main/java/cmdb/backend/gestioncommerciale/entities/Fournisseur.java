package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FOURNISSEUR")
public class Fournisseur extends Personne {
}
