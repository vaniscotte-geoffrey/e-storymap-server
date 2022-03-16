package fr.jaune.estorymap.model.document;

import fr.jaune.estorymap.model.DocumentType;

import javax.persistence.Entity;

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
    public boolean isCompatibleWith(Document document) {
        return document.isCompatibleWithBPMN(this);
    }

    @Override
    public boolean isCompatibleWithMFC(MFCDocument document) {
        return false;
    }

    @Override
    public boolean isCompatibleWithMCD(MCDDocument document) {
        return false;
    }
}
