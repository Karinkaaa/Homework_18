package dom_parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DOMParserDemo {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        /* создать класс Event, EventParameters, преобразовать указанный XML в List<Event> */

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream is = DOMParserDemo.class.getClassLoader().getResourceAsStream("data.xml");
        Document document = builder.parse(is);
        Element rootElement = document.getDocumentElement();

        System.out.println(" *** XML-file ***\n");
        printAllXmlNodes(rootElement, "\t");

        System.out.println("\n\n *** LIST ***\n");
        List<Event> eventsList = getListOfXmlNodes(rootElement.getChildNodes());

        for (Event event : eventsList)
            System.out.println(event);

    }

    private static List<Event> getListOfXmlNodes(NodeList childs) {

        Event event;
        List<Event> list = new ArrayList<Event>();

        for (int i = 0; i < childs.getLength(); i++) {

            Node eventNode = childs.item(i);

            if (eventNode.getNodeName().equalsIgnoreCase("event")) {

                event = new Event();
                NodeList childNodes = eventNode.getChildNodes();

                for (int k = 0; k < childNodes.getLength(); k++) {

                    Node eventChildNode = childNodes.item(k);

                    if (eventChildNode.getNodeName().equalsIgnoreCase("event_id")) {
                        event.setEventId(eventChildNode.getTextContent());

                    } else if (eventChildNode.getNodeName().equalsIgnoreCase("eventDate")) {
                        event.setEventDate(eventChildNode.getTextContent());

                    } else if (eventChildNode.getNodeName().equalsIgnoreCase("event_parameters")) {

                        NodeList nodeList = eventChildNode.getChildNodes();
                        EventParameters ep = event.getEventParameters();

                        for (int j = 0; j < nodeList.getLength(); j++) {

                            Node eventParamNode = nodeList.item(j);
                            String name = eventParamNode.getNodeName();
                            String value = eventParamNode.getTextContent();

                            if (name.equalsIgnoreCase("priority")) {
                                ep.setPriority(value);

                            } else if (name.equalsIgnoreCase("log_level")) {
                                ep.setLogLevel(value);

                            } else if (name.equalsIgnoreCase("source")) {
                                ep.setSource(value);
                            }
                        }
                    }
                }
                list.add(event);
            }
        }
        return list;
    }

    private static void printAllXmlNodes(Node rootNode, String prefix) {

        if (rootNode instanceof Element) {

            System.out.println(prefix + "<" + rootNode.getNodeName() + ">");

            for (int i = 0; i < rootNode.getChildNodes().getLength(); i++)
                printAllXmlNodes(rootNode.getChildNodes().item(i), prefix + "\t");

            System.out.println(prefix + "</" + rootNode.getNodeName() + ">");
        }
    }
}

