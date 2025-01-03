package cmdb.backend.gestioncommerciale.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvaDTO {
    private Long id;
    private double taux;
}

