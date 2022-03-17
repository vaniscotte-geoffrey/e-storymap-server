package fr.jaune.estorymap.document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BPMNReader extends XMLFileReader {

    private List<String> poolNames;

    public BPMNReader(String filepath) throws ParserConfigurationException, IOException, SAXException {
        super(filepath);
        this.poolNames = new ArrayList<>();

        final NodeList nodes = this.getElementsByTagName("mxCell");
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            final Node valueAttribute = node.getAttributes().getNamedItem("value");
            final Node styleAttribute = node.getAttributes().getNamedItem("style");
            if (valueAttribute != null && !valueAttribute.getNodeValue().equals("") && styleAttribute != null && styleAttribute.getNodeValue().contains("swimlane")) {
                this.poolNames.add(valueAttribute.getNodeValue());
            }
        }
    }

    public List<String> getPoolNames() {
        return poolNames;
    }
}
