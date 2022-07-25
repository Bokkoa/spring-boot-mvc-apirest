package bokkoa.backend.apirest.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bokkoa.backend.apirest.apirest.models.entity.Client;
import bokkoa.backend.apirest.apirest.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {
    
    @Autowired
    private IClientService clientService;

    private final Logger log = LoggerFactory.getLogger(ClientRestController.class);

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
            Client client = clientService.findById(id);
            String previousPhotoName = client.getPhoto();

            if(previousPhotoName != null && previousPhotoName.length() > 0){
               Path pathPreviousPhoto = Paths.get("uploads").resolve(previousPhotoName).toAbsolutePath();
               File previousPhotoFile = pathPreviousPhoto.toFile();

               if(previousPhotoFile.exists() && previousPhotoFile.canRead()){
                   previousPhotoFile.delete();
               }
            }

            clientService.delete(id);
        }catch(DataAccessException err){
            response.put("message", "DB query error");
            response.put("error", err.getMessage().concat(": ").concat(err.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Client deleted");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id ){

        Map<String, Object> response = new HashMap<>();

        Client client = clientService.findById(id);

        if(!file.isEmpty()){
            String fileName = UUID.randomUUID().toString() + '_' + file.getOriginalFilename().replace(" ", "");
            Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
            log.info(filePath.toString());
            try{
                Files.copy(file.getInputStream(), filePath);
            }catch( IOException err){
                response.put("message", "Error at uploading the image ");
                response.put("error", err.getMessage().concat(": ").concat(err.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
             }

             String previousPhotoName = client.getPhoto();

             if(previousPhotoName != null && previousPhotoName.length() > 0){
                Path pathPreviousPhoto = Paths.get("uploads").resolve(previousPhotoName).toAbsolutePath();
                File previousPhotoFile = pathPreviousPhoto.toFile();

                if(previousPhotoFile.exists() && previousPhotoFile.canRead()){
                    previousPhotoFile.delete();
                }
             }

            client.setPhoto(fileName);
            clientService.save(client);

            response.put("client", client);
            response.put("message", "Image uplaoded sucessfully");
        }
        return new ResponseEntity<Map<String, Object>>( response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{photoName:.+}")
    public ResponseEntity<Resource> seePhoto(@PathVariable String photoName){

        Path filePath = Paths.get("uploads").resolve(photoName).toAbsolutePath();
        log.info(filePath.toString());
        Resource resource = null;

        try{
            resource = new UrlResource(filePath.toUri());
        }catch(MalformedURLException err ){
            err.printStackTrace();
        }


        if(!resource.exists() && !resource.isReadable()){
            throw new RuntimeException("Can't load the image " + photoName);
        }

        HttpHeaders header = new HttpHeaders();

        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
