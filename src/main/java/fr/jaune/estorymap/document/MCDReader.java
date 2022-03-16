package fr.jaune.estorymap.document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MCDReader extends XMLFileReader {
    private List<String> datas;


    public MCDReader(String filepath) throws ParserConfigurationException, IOException, SAXException {
        super(filepath);
        this.datas = new ArrayList<>();

        final NodeList nodes = this.getElementsByTagName("mxCell");
        for (int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            final Node valueAttribute = node.getAttributes().getNamedItem("value");
            if (valueAttribute != null && !valueAttribute.getNodeValue().equals("")) {
                this.datas.add(valueAttribute.getNodeValue());
            }
        }
    }

    public List<String> getDatas() {
        return datas;
    }
}
