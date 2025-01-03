package cmdb.backend.gestioncommerciale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmdb.backend.gestioncommerciale.entities.Role;
import cmdb.backend.gestioncommerciale.services.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> creerRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.creerRole(role));
    }
}
