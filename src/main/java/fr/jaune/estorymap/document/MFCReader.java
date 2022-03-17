package fr.jaune.estorymap.document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MFCReader extends XMLFileReader {

    private List<String> fluxs;
    private List<String> actors;

    public MFCReader(String filepath) throws ParserConfigurationException, IOException, SAXException {
        super(filepath);
        this.fluxs = new ArrayList<>();
        this.actors = new ArrayList<>();
        final NodeList nodes = this.getElementsByTagName("mxCell");
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            final Node valueAttribute = node.getAttributes().getNamedItem("value");
            final Node styleAttribute = node.getAttributes().getNamedItem("style");
            if (valueAttribute != null && !valueAttribute.getNodeValue().equals("") && valueAttribute.getNodeValue().matches("F\\d+\\s-\\s.*")) {
                this.fluxs.add(valueAttribute.getNodeValue().replaceAll("F\\d+\\s-\\s", ""));
;           } else if (valueAttribute != null && !valueAttribute.getNodeValue().equals("") && !styleAttribute.getNodeValue().contains("swimlane")) {
                this.actors.add(valueAttribute.getNodeValue());
            }
        }
    }

    public List<String> getFluxs() {
        return fluxs;
    }

    public List<String> getActors() {
        return actors;
    }
}
