package bokkoa.backend.apirest.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileServiceImpl implements IUploadFileService{

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    private final static String UPLOAD_DIRECTORY = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        
        Path filePath = getPath(fileName);
        log.info(filePath.toString());
        Resource resource = null;

        resource = new UrlResource(filePath.toUri());


        if(!resource.exists() && !resource.isReadable()){

            // loading default image
            filePath = Paths.get("src/main/resource/static/image").resolve("noimage.png").toAbsolutePath();

            resource = new UrlResource(filePath.toUri());
            
            log.error("Can't load the image " + fileName);
        }


        return resource;
        
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        
        String fileName = UUID.randomUUID().toString() + '_' + file.getOriginalFilename().replace(" ", "");

        Path filePath = getPath(fileName);

        log.info(filePath.toString());

        Files.copy(file.getInputStream(), filePath);

        return fileName;

    }

    @Override
    public boolean delete(String fileName) {

        if(fileName != null && fileName.length() > 0){
            Path pathPreviousPhoto = Paths.get("uploads").resolve(fileName).toAbsolutePath();
            File previousPhotoFile = pathPreviousPhoto.toFile();

            if(previousPhotoFile.exists() && previousPhotoFile.canRead()){
                previousPhotoFile.delete();
                return true;
            }
         }

         return false;
    }

    @Override
    public Path getPath(String filename) {
        return Paths.get(UPLOAD_DIRECTORY).resolve(filename).toAbsolutePath();
    }
    
}
