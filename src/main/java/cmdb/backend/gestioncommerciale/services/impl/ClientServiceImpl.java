package cmdb.backend.gestioncommerciale.services.impl;

import cmdb.backend.gestioncommerciale.entities.Client;
import cmdb.backend.gestioncommerciale.repositories.PersonneRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClientServiceImpl extends AbstractPersonneService<Client> {

    public ClientServiceImpl(PersonneRepository<Client> repository) {
        super(repository, Client.class);
    }
}
