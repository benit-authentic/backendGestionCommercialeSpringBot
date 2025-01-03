package cmdb.backend.gestioncommerciale.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaiementCarteDTO extends PaiementDTO {
    private String numCarte;
    private String typeCarte;
}
