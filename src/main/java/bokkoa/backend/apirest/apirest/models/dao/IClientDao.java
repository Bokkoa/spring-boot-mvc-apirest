package bokkoa.backend.apirest.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bokkoa.backend.apirest.apirest.models.entity.Client;

public interface IClientDao extends JpaRepository<Client, Long> {
    
}
