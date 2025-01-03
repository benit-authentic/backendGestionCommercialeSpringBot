package cmdb.backend.gestioncommerciale.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmdb.backend.gestioncommerciale.entities.Role;
import cmdb.backend.gestioncommerciale.repositories.RoleRepository;
import cmdb.backend.gestioncommerciale.services.RoleService;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role creerRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleParNom(String nom) {
        return roleRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
    }
}

