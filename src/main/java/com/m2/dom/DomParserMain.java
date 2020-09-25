package com.m2.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

/**
 * Document Object Model: DOM parser is faster than SAX because it access whole XML document in memory.
 * Parsing XML file using DOM parser is quite fast if XML file is small but if you try to
 * read a large XML file using DOM parser there is more chances that it will take a long time
 *
 * Simple API parser: It’s recommended to use SAX XML parser for parsing large XML files in Java
 * because it doesn't require to load whole XML file in Java and it can read a big XML file in small parts
 *
 */
public class DomParserMain {
    public static void main(String[] args) throws Exception {

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Read the XML file to Document object.
        Document document = builder.parse(new File("src/main/resources/employees.xml"));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //XML validation is optional but good to have it before start parsing.
         Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            schema = schemaFactory.newSchema(new File("src/main/resources/empschema.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Validator validator = schema.newValidator();
        validator.validate(new DOMSource(document));


        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all employees
        NodeList nList = document.getElementsByTagName("employee");
        System.out.println("============================");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Print each employee's detail
                Element eElement = (Element) node;
                System.out.println("Employee id : " + eElement.getAttribute("id"));
                System.out.println("First Name : " + eElement.getElementsByTagName("firstName").item(0).getTextContent());
                System.out.println("Last Name : " + eElement.getElementsByTagName("lastName").item(0).getTextContent());
                System.out.println("Location : " + eElement.getElementsByTagName("location").item(0).getTextContent());
            }
        }

    }
}
