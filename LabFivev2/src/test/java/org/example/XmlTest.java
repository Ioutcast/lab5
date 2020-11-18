//package org.example;
//
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.io.*;
//import java.util.ArrayList;
//
//import static org.example.CommandMenu.commandManager;
//import static org.example.CommandMenu.mapCollection;
//import static org.junit.Assert.assertNotNull;
//
//public class XmlTest {
//    Worker worker = new Worker();
//
//    @Before
//    public void setUp() throws Exception {
//        mapCollection.clear();
//        ArrayList<String> arguments = new ArrayList<>();
//        Worker worker1 = new Worker();
//        worker1.setName("Test111");
//        mapCollection.put(1, worker1);
//        arguments.add("abc");
//        arguments.add("200");
//        arguments.add("100");
//        arguments.add("42");
//        arguments.add("16/08/2016");
//        arguments.add("1986-04-08 12:30");
//        arguments.add("RECOMMENDED_FOR_PROMOTION");
//        arguments.add("NO");
//        arguments.add("123");
//        arguments.add("PUBLIC");
//        arguments.add("street");
//        arguments.add("zipcode");
//        arguments.add("1");
//        arguments.add("2");
//        arguments.add("locName");
//        commandManager("update 1", arguments, true);
//        worker = mapCollection.get(1);
//
//    }
//    @Ignore
//    @Test
//    public void XmlCreateFromWorker() throws IOException {
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.writeValue(new File("simple_bean.xml"), worker);
//        File file = new File("simple_bean.xml");
//        assertNotNull(file);
//    }
//
//    @Ignore
//    @Test
//    public void WorkerCreateFromXML() throws IOException {
//        File file = new File("simple_bean.xml");
//        XmlMapper xmlMapper = new XmlMapper();
//        String xml = inputStreamToString(new FileInputStream(file));
//        Worker value = xmlMapper.readValue(xml, Worker.class);
//        System.out.println(value.toString());
//
//    }
//
//    public String inputStreamToString(InputStream is) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        String line;
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//        br.close();
//        return sb.toString();
//    }
//}
