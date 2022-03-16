package fr.jaune.estorymap.document;

import fr.jaune.estorymap.model.document.Document;

public class CompatibilityError {
    private Document targetDocument;
    private String errorDetail;

    public CompatibilityError(Document targetDocument) {
        this(targetDocument, "");
    }

    public CompatibilityError(Document targetDocument, String errorDetail) {
        this.targetDocument = targetDocument;
        this.errorDetail = errorDetail;
    }

    public Document getTargetDocument() {
        return targetDocument;
    }

    public void setTargetDocument(Document targetDocument) {
        this.targetDocument = targetDocument;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
