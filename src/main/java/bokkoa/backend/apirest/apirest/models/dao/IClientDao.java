package bokkoa.backend.apirest.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import bokkoa.backend.apirest.apirest.models.entity.Client;

public interface IClientDao extends CrudRepository<Client, Long> {
    
}
