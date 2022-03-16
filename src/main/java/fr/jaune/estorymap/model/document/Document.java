package fr.jaune.estorymap.model.document;

import fr.jaune.estorymap.model.DocumentType;
import org.springframework.lang.NonNull;
import org.xml.sax.SAXException;

import javax.persistence.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Entity
@Inheritance
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private DocumentType type;

    public Document() {
    }

    public Document(String path) {
        this(path, DocumentType.ANY);
    }

    public Document(String path, DocumentType type) {
        this.path = path;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        final String[] splits = this.path.split("/");
        return splits[splits.length - 1];
    }

    public String getPath() {
        return path;
    }

    public DocumentType getType() {
        return type;
    }

    public boolean isCompatibleWith(Document document) throws ParserConfigurationException, IOException, SAXException {
        return true;
    };

    public boolean isCompatibleWithMFC(MFCDocument document) throws ParserConfigurationException, IOException, SAXException{
        return true;
    };

    public boolean isCompatibleWithMCD(MCDDocument document) throws ParserConfigurationException, IOException, SAXException{
        return true;
    };

    public boolean isCompatibleWithBPMN(BPMNDocument document){
        return true;
    };
}
