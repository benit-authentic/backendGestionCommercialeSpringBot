package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.CategorieDTO;
import cmdb.backend.gestioncommerciale.entities.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface CategorieMapper {

    CategorieDTO toCategorieDTO(Categorie categorie);

    Categorie toCategorie(CategorieDTO dto);
}
