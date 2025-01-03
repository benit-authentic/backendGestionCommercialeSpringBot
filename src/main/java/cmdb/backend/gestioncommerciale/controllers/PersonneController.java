package cmdb.backend.gestioncommerciale.controllers;

import cmdb.backend.gestioncommerciale.entities.Client;
import cmdb.backend.gestioncommerciale.entities.Fournisseur;
import cmdb.backend.gestioncommerciale.entities.Personne;
import cmdb.backend.gestioncommerciale.services.impl.ClientServiceImpl;
import cmdb.backend.gestioncommerciale.services.impl.FournisseurServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final ClientServiceImpl clientService;
    private final FournisseurServiceImpl fournisseurService;

    public PersonneController(ClientServiceImpl clientService, FournisseurServiceImpl fournisseurService) {
        this.clientService = clientService;
        this.fournisseurService = fournisseurService;
    }

    // *** ENDPOINTS GÉNÉRIQUES ***

    @GetMapping
    public ResponseEntity<List<Personne>> findAllPersonnes() {
        // Combine clients et fournisseurs
        List<Personne> personnes = List.of();
        personnes.addAll(clientService.findAll());
        personnes.addAll(fournisseurService.findAll());
        return ResponseEntity.ok(personnes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> findPersonneById(@PathVariable Long id) {
        Personne personne = clientService.findById(id);
        if (personne == null) {
            personne = fournisseurService.findById(id);
        }
        return ResponseEntity.ok(personne);
    }

    // *** ENDPOINTS SPÉCIFIQUES À CLIENTS ***

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> findAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // *** ENDPOINTS SPÉCIFIQUES À FOURNISSEURS ***

    @PostMapping("/fournisseurs")
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return ResponseEntity.ok(fournisseurService.save(fournisseur));
    }

    @GetMapping("/fournisseurs")
    public ResponseEntity<List<Fournisseur>> findAllFournisseurs() {
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @GetMapping("/fournisseurs/{id}")
    public ResponseEntity<Fournisseur> findFournisseurById(@PathVariable Long id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @DeleteMapping("/fournisseurs/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
