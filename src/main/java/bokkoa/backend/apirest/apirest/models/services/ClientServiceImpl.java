package bokkoa.backend.apirest.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bokkoa.backend.apirest.apirest.models.dao.IClientDao;
import bokkoa.backend.apirest.apirest.models.entity.Client;

@Service
public class ClientServiceImpl implements IClientService {

    // DIY
    @Autowired
    private IClientDao clientDao;
    @Override
    @Transactional( readOnly = true)
    public List<Client> findAll() {
        // casting for return
        return (List<Client>)clientDao.findAll();
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
    
}