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
    @Mapping(target = "etatCommande", ignore = true) // État par défaut
    @Mapping(source = "ligneCommandes", target = "ligneCommandes") // Mapper les lignes
    Commande toEntity(CommandeCreateDTO dto);

	@Mapping(target = "id", ignore = true)
    @Mapping(target = "commande", ignore = true) // Éviter les boucles infinies
    @Mapping(target = "prixUnitaire", ignore = true) // Défini par le service
    @Mapping(target = "total", ignore = true) // Calculé par le service
    LigneCommande toEntity(CommandeCreateDTO.LigneCommandeCreateDTO dto);
	
	// 2. Mapping de Commande vers CommandResponseDTO (pour la réponse)
	
	@Mapping(source = "ligneCommandes", target = "ligneCommandes") // Mapper les lignes
    CommandeResponseDTO toResponseDTO(Commande commande);
	
	@Mapping(target = "produitId", source = "produit.id")
    @Mapping(target = "produitNom", source = "produit.nom")
    @Mapping(target = "total", expression = "java(ligneCommande.getUnitPrice().multiply(BigDecimal.valueOf(ligneCommande.getQuantity())))")
	CommandeResponseDTO.LigneCommandeResponseDTO toResponseDTO(LigneCommande ligneCommande);


    
    
    
}
