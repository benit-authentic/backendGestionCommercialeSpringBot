package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.Tva;
import cmdb.backend.gestioncommerciale.services.TvaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tvas")
@RequiredArgsConstructor
public class TvaController {

    private final TvaService tvaService;

    // Créer une nouvelle TVA
    @PostMapping
    public ResponseEntity<Tva> createTva(@RequestBody Tva tva) {
        Tva createdTva = tvaService.createTva(tva);
        return ResponseEntity.ok(createdTva);
    }

    // Mettre à jour une TVA existante
    @PutMapping("/{id}")
    public ResponseEntity<Tva> updateTva(@PathVariable Long id, @RequestBody Tva tva) {
        Tva updatedTva = tvaService.updateTva(id, tva);
        return ResponseEntity.ok(updatedTva);
    }

    // Récupérer une TVA par ID
    @GetMapping("/{id}")
    public ResponseEntity<Tva> getTvaById(@PathVariable Long id) {
        Tva tva = tvaService.findTvaById(id);
        return ResponseEntity.ok(tva);
    }

    // Récupérer toutes les TVA
    @GetMapping
    public ResponseEntity<List<Tva>> getAllTvas() {
        List<Tva> tvas = tvaService.findAllTvas();
        return ResponseEntity.ok(tvas);
    }

    // Supprimer une TVA par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTva(@PathVariable Long id) {
        tvaService.deleteTvaById(id);
        return ResponseEntity.noContent().build();
    }
}
