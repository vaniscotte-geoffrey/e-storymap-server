package fr.jaune.estorymap.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class StorageService {
    public final String FILES_PATH = "src/main/resources/uploaded-files/";
    public void store(MultipartFile file) throws IOException {
        final Path dest = Path.of(FILES_PATH + file.getOriginalFilename());
        file.transferTo(dest);
    }
    public String[] loadAll() {
        return new File(FILES_PATH).list();
    }

    public Resource loadAsResource(String filename) {
        return new FileSystemResource(filename);
    }
}
