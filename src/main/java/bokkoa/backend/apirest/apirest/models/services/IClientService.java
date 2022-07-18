package bokkoa.backend.apirest.apirest.models.services;

import java.util.List;

import bokkoa.backend.apirest.apirest.models.entity.Client;

public interface IClientService {
    public List<Client> findAll();
    public Client findById(Long id);
    public Client save(Client client);
    public void delete(Long id);

}
