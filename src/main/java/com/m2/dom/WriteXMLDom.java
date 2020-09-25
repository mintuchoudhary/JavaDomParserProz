package com.m2.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLDom {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder   = dbFactory.newDocumentBuilder();
        Document document = dBuilder.newDocument();

        //root element
        Element rootElement = document.createElement("employees");
        document.appendChild(rootElement);  //must to append the root element or xml will be created blank

        //child element
        Element childElement = document.createElement("employee");
        rootElement.appendChild(childElement);

        Attr firstNameAttr = document.createAttribute("firstName");
        firstNameAttr.setValue("Mintu");              // this will create <employee firstName="Mintu"/>
        childElement.setAttributeNode(firstNameAttr);

        // write the content into xml file

        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("src/main/resources/createdEmployee.xml"));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source,streamResult);
        System.out.println("File created successfully");

    }
}
