package cmdb.backend.gestioncommerciale.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import cmdb.backend.gestioncommerciale.dtos.PaiementCarteDTO;
import cmdb.backend.gestioncommerciale.dtos.PaiementChequeDTO;
import cmdb.backend.gestioncommerciale.dtos.PaiementDTO;
import cmdb.backend.gestioncommerciale.entities.Paiement;
import cmdb.backend.gestioncommerciale.entities.PaiementCarte;
import cmdb.backend.gestioncommerciale.entities.PaiementCheque;
import cmdb.backend.gestioncommerciale.entities.PaiementEspece;

@Mapper(componentModel = "spring")
public interface PaiementMapper {
	
	@Mapping(target = "typePaiement", expression = "java(getTypePaiement(paiement))")
    @Mapping(source = "facture.id", target = "factureId")
    PaiementDTO toPaiementDTO(Paiement paiement);
    
    default String getTypePaiement(Paiement paiement) {
        if (paiement instanceof PaiementCarte) {
            return "Carte";
        } else if (paiement instanceof PaiementCheque) {
            return "Chèque";
        } else if (paiement instanceof PaiementEspece) {
            return "Espèce";
        }
        return "Inconnu";
    }

    @Mapping(source = "facture.id", target = "factureId")
    @Mapping(target = "typePaiement", constant = "Carte")
    @Mapping(target = "numCarte", source = "numCarte")
    @Mapping(target = "typeCarte", source = "typeCarte")
    PaiementCarteDTO toPaiementCarteDTO(PaiementCarte paiementCarte);

    @Mapping(source = "facture.id", target = "factureId")
    @Mapping(target = "typePaiement", constant = "Cheque")
    @Mapping(target = "numCheque", source = "numCheque")
    PaiementChequeDTO toPaiementChequeDTO(PaiementCheque paiementCheque);
}
