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

    public MFCReader(String filepath) throws ParserConfigurationException, IOException, SAXException {
        super(filepath);
        this.fluxs = new ArrayList<>();
        final NodeList nodes = this.getElementsByTagName("mxCell");
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            final Node valueAttribute = node.getAttributes().getNamedItem("value");
            if (valueAttribute != null && !valueAttribute.getNodeValue().equals("") && valueAttribute.getNodeValue().matches("F\\d+\\s-\\s.*")) {
                this.fluxs.add(valueAttribute.getNodeValue().replaceAll("F\\d+\\s-\\s", ""));
;            }
        }
    }

    public List<String> getFluxs() {
        return fluxs;
    }
}
