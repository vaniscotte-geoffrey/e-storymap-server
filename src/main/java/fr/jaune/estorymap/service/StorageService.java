package fr.jaune.estorymap.service;

import fr.jaune.estorymap.model.document.Document;
import fr.jaune.estorymap.model.document.MCDDocument;
import fr.jaune.estorymap.model.document.MFCDocument;
import fr.jaune.estorymap.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StorageService {
    public final String FILES_PATH = "uploaded-files/";

    @Autowired private DocumentRepository documentRepository;

    public StorageService() {
        new File(FILES_PATH).mkdirs();
    }

    public void store(MultipartFile file) throws IOException {
        final Path dest = Path.of(FILES_PATH + file.getOriginalFilename());
        file.transferTo(dest);
        System.out.println("Dest" + dest.toString());

        if (Objects.requireNonNull(file.getOriginalFilename()).contains("mcd")) {
            this.documentRepository.save(new MCDDocument(dest.toString()));
        } else if (Objects.requireNonNull(file.getOriginalFilename()).contains("mfc")) {
            this.documentRepository.save(new MFCDocument(dest.toString()));
        } else {
            this.documentRepository.save(new Document(dest.toString()));
        }
    }
    public String[] loadAll() {
        this.documentRepository.findAll().forEach(System.out::println);
        return new File(FILES_PATH).list();
    }

    public void delete(String filename) {
        final File f = new File(this.FILES_PATH + filename);
        if (f.exists() && f.delete()) {
            this.documentRepository.findAll().forEach(document -> {
                System.out.println(f.getPath()  + " " + document.getPath());
                if (f.getPath().equals(document.getPath())) {
                    System.out.println("trouvé");
                    this.documentRepository.delete(document);
                }
            });
        }
    }

    public Resource loadAsResource(String filename) {
        return new FileSystemResource(filename);
    }
}
