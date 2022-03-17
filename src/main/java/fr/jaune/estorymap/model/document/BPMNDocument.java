package fr.jaune.estorymap.model.document;

import fr.jaune.estorymap.document.BPMNReader;
import fr.jaune.estorymap.document.MFCReader;
import fr.jaune.estorymap.model.DocumentType;
import org.xml.sax.SAXException;

import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Entity
public class BPMNDocument extends Document {

    public BPMNDocument() {
    }

    public BPMNDocument(String path) {
        super(path);
    }

    public BPMNDocument(String path, DocumentType type) {
        super(path, type);
    }

    @Override
    public boolean isCompatibleWith(Document document) throws ParserConfigurationException, IOException, SAXException {
        return document.isCompatibleWithBPMN(this);
    }

    @Override
    public boolean isCompatibleWithMFC(MFCDocument document) throws ParserConfigurationException, IOException, SAXException {
        final MFCReader mfc = new MFCReader(document.getPath());
        final BPMNReader bpmn = new BPMNReader(this.getPath());
        return bpmn.getPoolNames().containsAll(mfc.getActors());
    }
}
