package cmdb.backend.gestioncommerciale.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonneDTO {
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private String adresse;
    private String numCompte;
    private String typePersonne; // CLIENT ou FOURNISSEUR
    
    public void validateTypePersonne(PersonneDTO personneDTO, String expectedType) {
        if (!expectedType.equals(personneDTO.getTypePersonne())) {
            throw new RuntimeException("Invalid typePersonne: " + personneDTO.getTypePersonne());
        }
    }

}

