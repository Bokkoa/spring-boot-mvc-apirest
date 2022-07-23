package bokkoa.backend.apirest.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bokkoa.backend.apirest.apirest.models.entity.Client;
import bokkoa.backend.apirest.apirest.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {
    
    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public List<Client> index(){
        return clientService.findAll();
    }

    @GetMapping("/clients/page/{page}")
    public Page<Client> index(@PathVariable Integer page ){

        Pageable pageable = PageRequest.of(page, 10);
        return clientService.findAll( pageable );
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)  // REDUNDANT 
    public ResponseEntity<?> show(@PathVariable Long id){

        Client client = null;
        Map<String, Object> response = new HashMap<>();
        
        try{

         client = clientService.findById(id);
        }catch(DataAccessException err){
            response.put("message", "DB query error");
            response.put("error", err.getMessage().concat(": ").concat(err.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(client == null){
            response.put("message", "The client ID:".concat(id.toString().concat(" does not exists in db")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>( client , HttpStatus.OK);
   

    }

    @PostMapping("/clients")
    // @valid uses the decorators on entities
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result){  

        Client newClient = null;
        Map<String, Object> response = new HashMap<>();

        if( result.hasErrors()){
         
            // TRADITIONAL
            // List<String> errors = new ArrayList<>();
            // for (FieldError err : result.getFieldErrors()){
            //     errors.add(err.getDefaultMessage());
            // }
            // response.put("errors", errors);
            // return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

            // FUNCTIONAL
            List<String> errors = result.getFieldErrors()
                                    .stream()
                                    .map( err ->  "Field: " + err.getField() + ' ' + err.getDefaultMessage() )
                                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try{
            newClient = clientService.save(client);
        }catch(DataAccessException err){
            response.put("message", "DB query error");
            response.put("error", err.getMessage().concat(": ").concat(err.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client created");
        response.put("client", newClient);
        return new ResponseEntity<Map<String, Object>>( response, HttpStatus.CREATED);
    }


    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id){

        Client currentClient = clientService.findById(id);
        Map<String, Object> response = new HashMap<>();
        Client clientUpdated = null;

        if( result.hasErrors()){
            // FUNCTIONAL
            List<String> errors = result.getFieldErrors()
                                    .stream()
                                    .map( err ->  "Field: " + err.getField() + ' ' + err.getDefaultMessage() )
                                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(currentClient == null){
            response.put("message", "Can't update the client with ID:".concat(id.toString().concat(" does not exists in db")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            currentClient.setLastName(client.getLastName());
            currentClient.setEmail(client.getEmail());
            currentClient.setName(client.getName());
            currentClient.setCreatedAt(client.getCreatedAt());
            clientUpdated = clientService.save(currentClient);
        }catch(DataAccessException err){
            response.put("message", "DB query error");
            response.put("error", err.getMessage().concat(": ").concat(err.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "Client updated");
        response.put("client", clientUpdated);
        return new ResponseEntity<Map<String, Object>>( response, HttpStatus.CREATED);

    }


    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{
            clientService.delete(id);
        }catch(DataAccessException err){
            response.put("message", "DB query error");
            response.put("error", err.getMessage().concat(": ").concat(err.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Client deleted");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
