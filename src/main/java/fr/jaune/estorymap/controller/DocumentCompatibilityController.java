package fr.jaune.estorymap.controller;

import fr.jaune.estorymap.model.document.Document;
import fr.jaune.estorymap.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequestMapping("api/compatibility")
public class DocumentCompatibilityController {

    @Autowired
    private DocumentRepository documentRepository;

    public DocumentCompatibilityController() {
    }

    @GetMapping("/")
    public boolean projectIsCompatible() throws IOException, ParserConfigurationException, SAXException {
        for (Document doc : documentRepository.findAll()) {
            for (Document target : documentRepository.findAll()) {
                if (!doc.isCompatibleWith(target)) return false;
            }
        }
        return true;
    }
}
