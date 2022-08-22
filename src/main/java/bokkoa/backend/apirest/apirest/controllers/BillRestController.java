package bokkoa.backend.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bokkoa.backend.apirest.apirest.models.entity.Bill;
import bokkoa.backend.apirest.apirest.models.entity.Product;
import bokkoa.backend.apirest.apirest.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost:4200", "http://productionexample.com"})
@RestController
@RequestMapping("/api")
public class BillRestController {
    
    @Autowired
    private IClientService clientService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/bills/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Bill show(@PathVariable Long id){
        return clientService.findBillById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clientService.deleteBillById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/bills/filter-products/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterProducts(@PathVariable String term){
        return clientService.findProductByName(term);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill create(@RequestBody Bill bill){
        return clientService.saveBill(bill);
    }

}