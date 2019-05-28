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

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        /* создать класс Event, EventParameters, преобразовать указанный XML в List<Event> */

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream is = DOMParserDemo.class.getClassLoader().getResourceAsStream("data.xml");
        Document document = builder.parse(is);
        Element rootElement = document.getDocumentElement();

        //System.out.println(" *** XML-file ***\n\n");
        //printAllXmlNodes(rootElement.getChildNodes(), "\t");

        System.out.println("\n\n *** LIST ***");
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

                        event.setEventId(eventChildNode.getNodeName() + " = " + eventChildNode.getTextContent());

                    } else  if (eventChildNode.getNodeName().equalsIgnoreCase("eventDate")) {
                        event.setEventDate(eventChildNode.getNodeName() + " = " + eventChildNode.getTextContent());

                    } else  if (eventChildNode.getNodeName().equalsIgnoreCase("event_parameters")) {

                        NodeList nodeList = eventChildNode.getChildNodes();
                        EventParameters ep = event.getEventParameters();

                        for (int j = 0; j < nodeList.getLength(); j++) {

                            Node eventParamNode = nodeList.item(j);
                            String name = eventParamNode.getNodeName();
                            String value = eventParamNode.getTextContent();

                            if (name.equalsIgnoreCase("priority")) {
                                ep.setPriority(name + " = " + value);

                            } else if (name.equalsIgnoreCase("log_level")) {
                                ep.setLogLevel(name + " = " + value);

                            } else if (name.equalsIgnoreCase("source")) {
                                ep.setSource(name + " = " + value);
                            }
                        }
                    }
                }
                list.add(event);
            }
        }
        return list;
    }

    private static void printAllXmlNodes(NodeList childs, String prefix) {

        for (int i = 0; i < childs.getLength(); i++) {

            Node node = childs.item(i);

            if (node instanceof Element && node.hasAttributes()) {

                System.out.print(ANSI_YELLOW + prefix + "<" + node.getNodeName());
                NamedNodeMap attributes = node.getAttributes();

                for (int j = 0; j < attributes.getLength(); j++) {

                    Node attributeNode = attributes.item(j);
                    System.out.print(" " + ANSI_WHITE + attributeNode.getNodeName() + "=\"" + ANSI_GREEN +
                            attributeNode.getNodeValue() + "\"");
                }
                System.out.println(ANSI_YELLOW + "/>");

            } else if (node instanceof Element) {
                System.out.println(prefix + "<" + node.getNodeName() + ">");
            }

            printAllXmlNodes(node.getChildNodes(), prefix + "\t");
        }
    }
}

