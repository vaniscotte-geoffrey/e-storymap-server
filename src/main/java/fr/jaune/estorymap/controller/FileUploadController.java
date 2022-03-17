package fr.jaune.estorymap.controller;

import fr.jaune.estorymap.exceptions.StorageFileNotFoundException;
import fr.jaune.estorymap.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

@RestController
@RequestMapping("api/document")
public class FileUploadController {
    private final StorageService storageService;

    @Autowired FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String[] listUploadedFiles() throws IOException {
        return storageService.loadAll();
    }

    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(storageService.FILES_PATH + filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public void deleteFile(@PathVariable String filename) {
        storageService.delete(filename);
    }

    @PostMapping("/")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.store(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
