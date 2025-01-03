package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.ProduitDTO;
import cmdb.backend.gestioncommerciale.entities.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    @Mapping(source = "tva.id", target = "tvaId") // Associe l'ID de la TVA
    @Mapping(source = "tva.taux", target = "tvaTaux") // Associe le taux de TVA
    @Mapping(source = "categorie.id", target = "categorieId") // Associe l'ID de la catégorie
    @Mapping(source = "categorie.nom", target = "categorieNom") // Associe le nom de la catégorie
    ProduitDTO toProduitDTO(Produit produit);

    @Mapping(source = "tvaId", target = "tva.id") // Conversion inverse pour l'ID de TVA
    @Mapping(source = "categorieId", target = "categorie.id") // Conversion inverse pour l'ID de la catégorie
    Produit toProduit(ProduitDTO produitDTO);
}
