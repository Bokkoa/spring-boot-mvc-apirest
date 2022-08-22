package bokkoa.backend.apirest.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bokkoa.backend.apirest.apirest.models.entity.Bill;
import bokkoa.backend.apirest.apirest.models.entity.Client;
import bokkoa.backend.apirest.apirest.models.entity.Product;
import bokkoa.backend.apirest.apirest.models.entity.Region;

public interface IClientService {
    public List<Client> findAll();
    public Page<Client> findAll(Pageable pageable);
    public Client findById(Long id);
    public Client save(Client client);
    public void delete(Long id);

    public List<Region> findAllRegions();

    public Bill findBillById(Long id);
    public Bill saveBill(Bill bill);

    public void deleteBillById(Long id);

    public List<Product> findProductByName(String term);


}
