package bokkoa.backend.apirest.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bokkoa.backend.apirest.apirest.models.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
    
    // manual case
    @Query("select p from Product p where p.name like %?1%")
    public List<Product> findByName(String term);

    // with classlib jpa
    public List<Product> findByNameContainingIgnoreCase(String term);

    // starting with term pattern jpa solution
    public List<Product> findByNameStartingWithIgnoreCase(String term);

}
