package cmdb.backend.gestioncommerciale.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class PaiementCheque extends Paiement {
    private String numCheque;
}
