package fr.jaune.estorymap.document;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLFileReader {
    private String filepath;
    private Document document;

    public XMLFileReader(String filepath) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Reader"+filepath);
        this.filepath = filepath;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        System.out.println(filepath);
        this.document = db.parse(new File(filepath));
        this.document.getDocumentElement().normalize();
    }

    public String getFilepath() {
        return filepath;
    }

    public Document getDocument() {
        return document;
    }

    public Element getDocumentElement() {
        return getDocument().getDocumentElement();
    }

    public NodeList getElementsByTagName(String tagname) {
        return getDocument().getElementsByTagName(tagname);
    }

    public Element getElementById(String elementId) {
        return getDocument().getElementById(elementId);
    }
}
