package cmdb.backend.gestioncommerciale.services;

import cmdb.backend.gestioncommerciale.entities.Role;

public interface RoleService {
    Role creerRole(Role role);
    Role getRoleParNom(String nom);
}

