package fr.jaune.estorymap.service;

import fr.jaune.estorymap.model.document.BPMNDocument;
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

    public void loadFileInDatabase() {
        for (String filename : this.loadAll()) {
            if (Objects.requireNonNull(filename).contains("mcd")) {
                this.documentRepository.save(new MCDDocument(FILES_PATH + filename));
            } else if (Objects.requireNonNull(filename).contains("mfc")) {
                this.documentRepository.save(new MFCDocument(FILES_PATH + filename));
            } else if (Objects.requireNonNull(filename).contains("bpmn")) {
                this.documentRepository.save(new BPMNDocument(FILES_PATH + filename));
            } else {
                this.documentRepository.save(new Document(FILES_PATH + filename));
            }
        }
    }

    public void store(MultipartFile file) throws IOException {
        final Path dest = Path.of(FILES_PATH + file.getOriginalFilename());
        file.transferTo(dest);
        if (Objects.requireNonNull(file.getOriginalFilename()).contains("mcd")) {
            this.documentRepository.save(new MCDDocument(dest.toString()));
        } else if (Objects.requireNonNull(file.getOriginalFilename()).contains("mfc")) {
            this.documentRepository.save(new MFCDocument(dest.toString()));
        } else if (Objects.requireNonNull(file.getOriginalFilename()).contains("bpmn")) {
            this.documentRepository.save(new BPMNDocument(dest.toString()));
        } else {
            this.documentRepository.save(new Document(dest.toString()));
        }
    }
    public String[] loadAll() {
        return new File(FILES_PATH).list();
    }

    public void delete(String filename) {
        final File f = new File(this.FILES_PATH + filename);
        if (f.exists() && f.delete()) {
            this.documentRepository.findAll().forEach(document -> {
                if (f.getPath().equals(document.getPath())) {
                    this.documentRepository.delete(document);
                }
            });
        }
    }

    public Resource loadAsResource(String filename) {
        return new FileSystemResource(filename);
    }
}
