package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.CategorieDTO;
import cmdb.backend.gestioncommerciale.entities.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface CategorieMapper {

    CategorieDTO toCategorieDTO(Categorie categorie);

    @Mapping(target = "produits", ignore = true)
    Categorie toCategorie(CategorieDTO dto);
}
