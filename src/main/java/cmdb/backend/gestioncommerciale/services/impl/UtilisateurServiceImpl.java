package cmdb.backend.gestioncommerciale.services.impl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmdb.backend.gestioncommerciale.entities.Role;
import cmdb.backend.gestioncommerciale.entities.Utilisateur;
import cmdb.backend.gestioncommerciale.repositories.RoleRepository;
import cmdb.backend.gestioncommerciale.repositories.UtilisateurRepository;
import cmdb.backend.gestioncommerciale.services.UtilisateurService;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    @Override
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        // Hash du mot de passe avant sauvegarde
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void attribuerRole(String username, String roleNom) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Role role = roleRepository.findByNom(roleNom)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        utilisateur.getRoles().add(role);
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurParUsername(String username) {
        return utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public List<Utilisateur> listeUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}

