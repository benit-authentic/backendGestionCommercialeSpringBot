package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.FicheStockDTO;
import cmdb.backend.gestioncommerciale.entities.FicheStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FicheStockMapper {

    @Mapping(source = "produit.id", target = "produitId")          // Mappe l'ID du produit
    @Mapping(source = "produit.nom", target = "produitNom")       // Mappe le nom du produit
    @Mapping(source = "personne.id", target = "personneId")       // Mappe l'ID de la personne
    @Mapping(source = "personne.nom", target = "personneNom")     // Mappe le nom de la personne
    FicheStockDTO toFicheStockDTO(FicheStock ficheStock);

    @Mapping(source = "produitId", target = "produit.id")         // Associe le produit par ID
    @Mapping(source = "personneId", target = "personne.id")       // Associe la personne par ID
    FicheStock toFicheStock(FicheStockDTO ficheStockDTO);
}
