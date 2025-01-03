package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class PaiementCarte extends Paiement {
    private String numCarte;
    private String typeCarte;
}
