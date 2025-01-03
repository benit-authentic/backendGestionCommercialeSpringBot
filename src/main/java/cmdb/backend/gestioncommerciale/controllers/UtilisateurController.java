package cmdb.backend.gestioncommerciale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cmdb.backend.gestioncommerciale.entities.Utilisateur;
import cmdb.backend.gestioncommerciale.services.UtilisateurService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<Utilisateur> creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.creerUtilisateur(utilisateur));
    }

    @PostMapping("/roles")
    public ResponseEntity<?> attribuerRole(@RequestParam String username, @RequestParam String roleNom) {
        utilisateurService.attribuerRole(username, roleNom);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> listeUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.listeUtilisateurs());
    }
}

