package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.PersonneDTO;
import cmdb.backend.gestioncommerciale.entities.Client;
import cmdb.backend.gestioncommerciale.entities.Fournisseur;
import cmdb.backend.gestioncommerciale.entities.Personne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonneMapper {

    // Conversion de Personne (Client ou Fournisseur) vers PersonneDTO
    @Mapping(target = "typePersonne", expression = "java(getTypePersonne(personne))")
    PersonneDTO toPersonneDTO(Personne personne);
    
    default String getTypePersonne(Personne personne) {
        if (personne instanceof Client) {
            return "CLIENT";
        } else if (personne instanceof Fournisseur) {
            return "FOURNISSEUR";
        }
        return "INCONNU";
    }

    // Conversion de PersonneDTO vers Personne (Client ou Fournisseur)
    default Personne toPersonne(PersonneDTO personneDTO) {
        if ("CLIENT".equals(personneDTO.getTypePersonne())) {
            return toClient(personneDTO);
        } else if ("FOURNISSEUR".equals(personneDTO.getTypePersonne())) {
            return toFournisseur(personneDTO);
        }
        throw new IllegalArgumentException("TypePersonne inconnu : " + personneDTO.getTypePersonne());
    }

    // Méthodes spécifiques pour chaque type
    @Mapping(target = "commandes", ignore = true)
    Client toClient(PersonneDTO personneDTO);
    
    @Mapping(target = "commandes", ignore = true)
    Fournisseur toFournisseur(PersonneDTO personneDTO);
}
