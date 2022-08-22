package bokkoa.backend.apirest.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bokkoa.backend.apirest.apirest.models.dao.IBillDao;
import bokkoa.backend.apirest.apirest.models.dao.IClientDao;
import bokkoa.backend.apirest.apirest.models.dao.IProductDao;
import bokkoa.backend.apirest.apirest.models.entity.Bill;
import bokkoa.backend.apirest.apirest.models.entity.Client;
import bokkoa.backend.apirest.apirest.models.entity.Product;
import bokkoa.backend.apirest.apirest.models.entity.Region;

@Service
public class ClientServiceImpl implements IClientService {

    // DIY
    @Autowired
    private IClientDao clientDao;

    @Autowired
    private IBillDao billDao;

    @Autowired
    private IProductDao productDao;

    @Override
    @Transactional( readOnly = true)
    public List<Client> findAll() {
        // casting for return
        return (List<Client>)clientDao.findAll();
    }

    @Override
    @Transactional( readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return clientDao.findAll(pageable);
    }
    
    @Override
    @Transactional( readOnly = true)
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientDao.save(client);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        clientDao.deleteById(id);        
    }

    @Override
    @Transactional(readOnly=true)
    public List<Region> findAllRegions() {
        return clientDao.findAllRegions();
    }

    @Override
    public Bill findBillById(Long id) {
        return billDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Bill saveBill(Bill bill) {
        return billDao.save(bill);
    }

    @Override
    @Transactional
    public void deleteBillById(Long id) {
        billDao.deleteById(id);
    }

   

    @Override
    public List<Product> findProductByName(String term) {
        return productDao.findByName(term);
    }

 
    
}
