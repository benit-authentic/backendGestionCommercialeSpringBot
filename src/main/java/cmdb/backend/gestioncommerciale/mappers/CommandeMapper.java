package cmdb.backend.gestioncommerciale.mappers;

import cmdb.backend.gestioncommerciale.dtos.CommandeCreateDTO;
import cmdb.backend.gestioncommerciale.dtos.CommandeResponseDTO;
import cmdb.backend.gestioncommerciale.entities.Commande;
import cmdb.backend.gestioncommerciale.entities.LigneCommande;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
	
	 // 1. Mapping de CommandCreateDTO vers Commande (pour la création)
	
	@Mapping(target = "id", ignore = true) // ID auto-généré
    @Mapping(target = "dateCommande", ignore = true) // Date auto-générée
    @Mapping(target = "etat", ignore = true) // État par défaut
    @Mapping(target = "facture", ignore = true) // Facture créée plus tard
    @Mapping(target = "personne", ignore = true) // Géré par le service
    @Mapping(source = "ligneCommandes", target = "ligneCommandes") // Mapper les lignes
    Commande toEntity(CommandeCreateDTO dto);

	@Mapping(target = "id", ignore = true)
    @Mapping(target = "commande", ignore = true) // Éviter les boucles infinies
    @Mapping(target = "prixUnitaire", ignore = true) // Défini par le service
    @Mapping(target = "produit", ignore = true) // Géré par le service
    LigneCommande toEntity(CommandeCreateDTO.LigneCommandeCreateDTO dto);
	
	// 2. Mapping de Commande vers CommandResponseDTO (pour la réponse)
	
	@Mapping(source = "personne.id", target = "personneId")
    @Mapping(target = "personneType", expression = "java(getPersonneType(commande.getPersonne()))")
    @Mapping(source = "ligneCommandes", target = "ligneCommandes") // Mapper les lignes
    CommandeResponseDTO toResponseDTO(Commande commande);
    
    default String getPersonneType(cmdb.backend.gestioncommerciale.entities.Personne personne) {
        if (personne instanceof cmdb.backend.gestioncommerciale.entities.Client) {
            return "CLIENT";
        } else if (personne instanceof cmdb.backend.gestioncommerciale.entities.Fournisseur) {
            return "FOURNISSEUR";
        }
        return "INCONNU";
    }
    
	@Mapping(target = "produitId", source = "produit.id")
    @Mapping(target = "produitNom", source = "produit.nom")
    @Mapping(target = "prixUnitaire", source = "prixUnitaire")
    @Mapping(target = "quantite", source = "quantite")
    @Mapping(target = "total", expression = "java(ligneCommande.getPrixUnitaire().multiply(java.math.BigDecimal.valueOf(ligneCommande.getQuantite())))")
	CommandeResponseDTO.LigneCommandeResponseDTO toResponseDTO(LigneCommande ligneCommande);


    
    
    
}
