package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.Fournisseur;
import cmdb.backend.gestioncommerciale.repositories.PersonneRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FournisseurServiceImpl extends AbstractPersonneService<Fournisseur> {

    public FournisseurServiceImpl(PersonneRepository<Fournisseur> repository) {
        super(repository, Fournisseur.class);
    }
}
