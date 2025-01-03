package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.FactureResponseDTO;
import cmdb.backend.gestioncommerciale.entities.Facture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommandeMapper.class)
public interface FactureMapper {

    @Mapping(target = "commandeId", source = "facture.commande.id")
    @Mapping(target = "typeCommande", source = "facture.commande.typeCommande")
    @Mapping(target = "personneId", source = "facture.commande.personne.id")
    @Mapping(target = "personneType", source = "facture.commande.personne.type")
    @Mapping(target = "ligneCommandes", source = "facture.commande.ligneCommandes")
    @Mapping(target = "soldeRestant", expression = "java(facture.calculerSoldeRestant())")
    FactureResponseDTO toDto(Facture facture);
}
