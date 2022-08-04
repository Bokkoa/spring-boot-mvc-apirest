package bokkoa.backend.apirest.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bokkoa.backend.apirest.apirest.models.entity.Client;
import bokkoa.backend.apirest.apirest.models.entity.Region;

public interface IClientDao extends JpaRepository<Client, Long> {

    // NOTATION TO POINT REGION ENTITY AND GET LINKEDS
    @Query("from Region")
    public List<Region> findAllRegions();
}
