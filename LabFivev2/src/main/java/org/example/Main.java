package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;



/**
 * Главный класс.
 */
public class Main {
    private static CommandMenu commandMenu;

    private static File xml = null;

    /**
     * Точка входа.
     *
     * @param args аргументы из командной строки
     */
    public static void main(String[] args) throws Exception {
        commandMenu = new CommandMenu();
        xml = new File(args[0]);
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        while (!xml.exists()) {
            System.out.println("Файл не найден");
            xml = new File(reader.readLine());
        }

        while (!xml.canWrite() || !xml.canRead()) {
            System.out.println("Недостаточно прав на файл");
            xml = new File(reader.readLine());
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Worker");
            NodeList nodeList2 = doc.getElementsByTagName("Coordinates");
            NodeList nodeList3 = doc.getElementsByTagName("Organization");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Worker worker = new Worker();

                getWorker(worker, nodeList.item(i));
                worker.setCoordinates(getCoordinates(nodeList2.item(i)));

                StringWriter buf = CheckOrganizationNull(nodeList3, i);

                if (!buf.toString().replace("\r", "").replace("\n", "").equals("<Organization/>"))
                    worker.setOrganization(getOrganization(nodeList3.item(i)));
                else worker.setOrganization(null);

                commandMenu.getMapCollection().put(i, worker);

            }
            System.out.println("Произошла загрузка Worker в коллекцию.");
        } catch (Exception e) {
            System.out.println("Файл нельзя обработать");
        }

        while (true) {
            try {
                System.out.println("Введите команду:");
                String command = reader.readLine();
                if (command.equals("exit")) {
                    break;
                }
                commandMenu.commandManager(command, new ArrayList<>(), false);

            } catch (NumberFormatException e) {
                System.out.println("Значение введено неверно");
            }
        }

    }

    private static StringWriter CheckOrganizationNull(NodeList nodeList3, int i) throws TransformerException {
        Node elem = nodeList3.item(i);//Your Node
        StringWriter buf = new StringWriter();
        Transformer xform = TransformerFactory.newInstance().newTransformer();
        xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // optional
        xform.setOutputProperty(OutputKeys.INDENT, "yes"); // optional
        xform.transform(new DOMSource(elem), new StreamResult(buf));
        return buf;
    }

    /**
     * @param worker
     * @param node
     */
    private static void getWorker(Worker worker, Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            worker.setName(getTagValue("Name", element));
            worker.setSalary(Double.parseDouble(getTagValue("Salary", element)));
            worker.setStartDate(LocalDate.parse(getTagValue("StartDate", element)));
            worker.setEndDate(LocalDateTime.parse(getTagValue("EndDate", element)));
            worker.setStatus(Status.valueOf(getTagValue("Status", element)));
        }
    }

    /**
     * @param node
     * @return
     */
    private static Coordinates getCoordinates(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            return new Coordinates(Integer.parseInt(getTagValue("x", element)), Integer.parseInt(getTagValue("y", element)));
        }
        return null;
    }

    /**
     * @param node
     * @return
     */
    private static Organization getOrganization(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            return new Organization(Long.parseLong(getTagValue("EmployeesCount", element)), OrganizationType.valueOf(getTagValue("Type", element)),
                    new Address(getTagValue("ZipCode", element), getTagValue("Street", element),
                            new Location(Double.parseDouble(getTagValue("X", element)), Integer.parseInt(getTagValue("Y", element)), getTagValue("Name", element)))
            );
        }
        return null;
    }

    /**
     * @param tag
     * @param element
     * @return
     */
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
