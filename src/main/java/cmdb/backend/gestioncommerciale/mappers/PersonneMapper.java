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
    @Mapping(source = "commandes", target = "commandes")
    @Mapping(source = "TYPE_PERSONNE", target = "typePersonne") // Mappe le type discriminant
    PersonneDTO toPersonneDTO(Personne personne);

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
    Client toClient(PersonneDTO personneDTO);
    Fournisseur toFournisseur(PersonneDTO personneDTO);
}
