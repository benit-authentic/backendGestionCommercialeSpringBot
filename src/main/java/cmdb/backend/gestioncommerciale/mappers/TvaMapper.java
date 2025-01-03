package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.TvaDTO;
import cmdb.backend.gestioncommerciale.entities.Tva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface TvaMapper {

    TvaDTO toTvaDTO(Tva tva);

    Tva toTva(TvaDTO dto);
}
