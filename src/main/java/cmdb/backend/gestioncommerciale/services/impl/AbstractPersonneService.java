package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.Personne;
import cmdb.backend.gestioncommerciale.repositories.PersonneRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public abstract class AbstractPersonneService<T extends Personne> {

    private final PersonneRepository<T> repository;
    private final Class<T> type;

    protected AbstractPersonneService(PersonneRepository<T> repository, Class<T> type) {
        this.repository = Objects.requireNonNull(repository);
        this.type = Objects.requireNonNull(type);
    }

    protected T castPersonne(Personne personne) {
        if (!type.isInstance(personne)) {
            throw new IllegalArgumentException("L'objet fourni n'est pas une instance de " + type.getSimpleName());
        }
        return type.cast(personne);
    }

    public T save(T personne) {
        return repository.save(personne);
    }

    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personne not found with id: " + id));
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<T> findByNom(String nom) {
        return repository.findByNom(nom);
    }
}
