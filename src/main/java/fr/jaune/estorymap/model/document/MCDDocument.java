package fr.jaune.estorymap.model.document;

import fr.jaune.estorymap.document.MCDReader;
import fr.jaune.estorymap.document.MFCReader;
import fr.jaune.estorymap.model.DocumentType;
import org.xml.sax.SAXException;

import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Entity
public class MCDDocument extends Document{

    public MCDDocument() {
    }

    public MCDDocument(String path) {
        super(path);
    }

    public MCDDocument(String path, DocumentType type) {
        super(path, type);
    }

    @Override
    public boolean isCompatibleWith(Document document) throws ParserConfigurationException, IOException, SAXException {
        return document.isCompatibleWithMCD(this);
    }

    @Override
    public boolean isCompatibleWithMFC(MFCDocument document) throws ParserConfigurationException, IOException, SAXException {
        final MFCReader mfc = new MFCReader(document.getPath());
        final MCDReader mcd = new MCDReader(this.getPath());
        return mcd.getDatas().containsAll(mfc.getFluxs());
    }
}
