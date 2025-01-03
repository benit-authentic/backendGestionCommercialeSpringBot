package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.Tva;
import cmdb.backend.gestioncommerciale.repositories.TvaRepository;
import cmdb.backend.gestioncommerciale.services.TvaService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TvaServiceImpl implements TvaService {

    private final TvaRepository tvaRepository;

    @Override
    public Tva createTva(Tva tva) {
        validateTva(tva);
        return tvaRepository.save(tva);
    }

    @Override
    public Tva updateTva(Long id, Tva tva) {
        Tva existingTva = tvaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tva not found with id: " + id));
        validateTva(tva);
        existingTva.setTaux(tva.getTaux());
        return tvaRepository.save(existingTva);
    }

    @Override
    public Tva findTvaById(Long id) {
        return tvaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tva not found with id: " + id));
    }

    @Override
    public List<Tva> findAllTvas() {
        return tvaRepository.findAll();
    }

    @Override
    public void deleteTvaById(Long id) {
        if (!tvaRepository.existsById(id)) {
            throw new RuntimeException("Tva not found with id: " + id);
        }
        tvaRepository.deleteById(id);
    }

    /**
     * Valide que le taux de TVA est positif.
     */
    private void validateTva(Tva tva) {
        if (tva.getTaux() < 0) {
            throw new RuntimeException("Le taux de TVA doit être positif.");
        }
    }
}