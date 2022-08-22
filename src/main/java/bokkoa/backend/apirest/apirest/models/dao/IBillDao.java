package bokkoa.backend.apirest.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import bokkoa.backend.apirest.apirest.models.entity.Bill;

public interface IBillDao extends CrudRepository<Bill, Long> {
    
}
