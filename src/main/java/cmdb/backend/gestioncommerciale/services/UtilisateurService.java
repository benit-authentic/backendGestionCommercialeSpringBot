package cmdb.backend.gestioncommerciale.services;

import java.util.List;

import cmdb.backend.gestioncommerciale.entities.Utilisateur;

public interface UtilisateurService {
    Utilisateur creerUtilisateur(Utilisateur utilisateur);
    void attribuerRole(String username, String roleNom);
    Utilisateur getUtilisateurParUsername(String username);
    List<Utilisateur> listeUtilisateurs();
}

