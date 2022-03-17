package fr.jaune.estorymap.model.document;

import fr.jaune.estorymap.document.MCDReader;
import fr.jaune.estorymap.document.MFCReader;
import fr.jaune.estorymap.model.DocumentType;
import org.xml.sax.SAXException;

import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Entity
public class MFCDocument extends Document {

    public MFCDocument() {
    }

    public MFCDocument(String path) {
        super(path);
    }

    public MFCDocument(String path, DocumentType type) {
        super(path, type);
    }

    @Override
    public boolean isCompatibleWith(Document document) throws ParserConfigurationException, IOException, SAXException {
        return document.isCompatibleWithMFC(this);
    }

    @Override
    public boolean isCompatibleWithMCD(MCDDocument document) throws ParserConfigurationException, IOException, SAXException {
        final MFCReader mfc = new MFCReader(this.getPath());
        final MCDReader mcd = new MCDReader(document.getPath());
        return mcd.getDatas().containsAll(mfc.getFluxs());
    }

    @Override
    public boolean isCompatibleWithBPMN(BPMNDocument document) throws ParserConfigurationException, IOException, SAXException {
        return document.isCompatibleWithMFC(this);
    }
}
