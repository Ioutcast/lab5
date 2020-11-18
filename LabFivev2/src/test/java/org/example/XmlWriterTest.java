package org.example;

import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.example.XmlWriteToFile.xmlWriterToFile;


public class XmlWriterTest {
    public Map<Integer, Worker> mapCollection1 = new LinkedHashMap<>();

    File newXml = new File("newXml.xml");

    public XmlWriterTest() throws FileNotFoundException {
    }

    @Before
    public void setUp() throws Exception {
        CommandMenu commandMenu = new CommandMenu();
        commandMenu.getMapCollection().clear();
        ArrayList<String> arguments = new ArrayList<>();
        Worker worker = new Worker();
        worker.setName("Test");
        commandMenu.getMapCollection().put(1, worker);
        arguments.add("abc");
        arguments.add("200");
        arguments.add("100");
        arguments.add("42");
        arguments.add("16/08/2016");
        arguments.add("1986-04-08 12:30");
        arguments.add("RECOMMENDED_FOR_PROMOTION");
        arguments.add("NO");
        arguments.add("123");
        arguments.add("PUBLIC");
        arguments.add("street");
        arguments.add("zipcode");
        arguments.add("1");
        arguments.add("2");
        arguments.add("locName");
        commandMenu.commandManager("update 1", arguments, true);


        Worker worker1 = new Worker();
        commandMenu.getMapCollection().put(2, worker1);
        arguments.add("abc");
        arguments.add("200");
        arguments.add("100");
        arguments.add("42");
        arguments.add("16/08/2016");
        arguments.add("1986-04-08 12:30");
        arguments.add("RECOMMENDED_FOR_PROMOTION");
        arguments.add("");

        commandMenu.commandManager("update 2", arguments, true);

        mapCollection1.putAll(commandMenu.getMapCollection());

    }

    @Test
    public void XmlWriterTestTrue() throws TransformerException, ParserConfigurationException, IOException {
        xmlWriterToFile(mapCollection1, newXml);
    }
}
