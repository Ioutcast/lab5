package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Map;


/**
 * Класс для преобразования полей рабочего
 * к полями xml файла. Создание нового xml флайла,
 * запись в него класса Worker
 */
public class XmlWriteToFile {
    /**
     * @param mapCollection
     * @param file          outputstream
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws UnsupportedEncodingException
     */
    public static void xmlWriterToFile(Map<Integer, Worker> mapCollection, File file) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("Workers");
        doc.appendChild(root);
        System.out.println(mapCollection.toString());
        for (Map.Entry<Integer, Worker> workerEntry : mapCollection.entrySet()) {
            root.appendChild(createUser(doc, workerEntry.getValue()));
        }

        StringWriter sw = new StringWriter();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        String result = sw.toString();
        FileOutputStream fos = new FileOutputStream(file);
        Writer outputStreamWriter = new OutputStreamWriter(fos, "UTF-8");
        outputStreamWriter.write(result);
        outputStreamWriter.close();

        // byte[] buffer = result.getBytes();
        // fos.write(buffer, 0, buffer.length);
        //DOMSource source = new DOMSource(doc);
        // StreamResult my_file = new StreamResult(new OutputStreamWriter(out, "UTF-8"));
        // StreamResult my_file = new StreamResult(file);
        // transformer.transform(source, my_file);
    }

    /**
     * @param doc
     * @param worker
     * @return workerElem
     */
    private static Node createUser(Document doc, Worker worker) {
        Element workerElem = doc.createElement("Worker");
        Element coordinatesElem = doc.createElement("Coordinates");
        Element organizationElem = doc.createElement("Organization");
        Element addressEmel = doc.createElement("Address");
        Element locationElem = doc.createElement("Location");

        workerElem.appendChild(createUserElement(doc, "id", String.valueOf(worker.getId())));
        workerElem.appendChild(createUserElement(doc, "Name", worker.getName()));

        coordinatesElem.appendChild(createUserElement(doc, "x", String.valueOf(worker.getCoordinates().getX())));
        coordinatesElem.appendChild(createUserElement(doc, "y", String.valueOf(worker.getCoordinates().getY())));

        workerElem.appendChild(createUserElement(doc, "CreationDate", worker.getCreationDate().toString()));
        workerElem.appendChild(createUserElement(doc, "Salary", worker.getSalary().toString()));
        workerElem.appendChild(createUserElement(doc, "StartDate", worker.getStartDate().toString()));
        workerElem.appendChild(createUserElement(doc, "EndDate", worker.getEndDate().toString()));
        workerElem.appendChild(createUserElement(doc, "Status", worker.getStatus().toString()));
        if (worker.getOrganization() != null) {
            organizationElem.appendChild(createUserElement(doc, "EmployeesCount", worker.getOrganization().getEmployeesCount().toString()));
            organizationElem.appendChild(createUserElement(doc, "Type", worker.getOrganization().getType().toString()));

            addressEmel.appendChild(createUserElement(doc, "ZipCode", worker.getOrganization().getOfficialAddress().getZipCode()));
            addressEmel.appendChild(createUserElement(doc, "Street", worker.getOrganization().getOfficialAddress().getStreet()));

            locationElem.appendChild(createUserElement(doc, "X", worker.getOrganization().getOfficialAddress().getTown().getX().toString()));
            locationElem.appendChild(createUserElement(doc, "Y", String.valueOf(worker.getOrganization().getOfficialAddress().getTown().getY())));
            locationElem.appendChild(createUserElement(doc, "Name", worker.getOrganization().getOfficialAddress().getTown().getName()));

            workerElem.appendChild(coordinatesElem);
            workerElem.appendChild(organizationElem);
            organizationElem.appendChild(addressEmel);
            addressEmel.appendChild(locationElem);
        } else {
            workerElem.appendChild(coordinatesElem);
            workerElem.appendChild(organizationElem);
        }
        return workerElem;
    }

    /**
     * @param document
     * @param name
     * @param value
     * @return node
     */
    private static Node createUserElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }
}
