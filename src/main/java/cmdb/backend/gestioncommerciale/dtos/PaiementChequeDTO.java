package cmdb.backend.gestioncommerciale.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaiementChequeDTO extends PaiementDTO {
    private String numCheque;
}