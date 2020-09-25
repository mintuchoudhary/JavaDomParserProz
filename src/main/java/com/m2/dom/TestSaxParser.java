package com.m2.dom;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class TestSaxParser {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        //Locate the file
        File xmlFile = new File("src/main/resources/employees.xml");

        //Create the parser instance
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        //create handler to read only selected tag from file and then read files
        EmpHandler empHandler = new EmpHandler();
        saxParser.parse(xmlFile, empHandler);


    }
}

class EmpHandler extends DefaultHandler {
    boolean firstName = false;
    boolean lastName = false;
    boolean location = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("employee"))
            System.out.println("  Id : " + attributes.getValue("id"));
        else if (qName.equalsIgnoreCase("firstname"))
            firstName = true;
        else if (qName.equalsIgnoreCase("lastname"))
            lastName = true;
        else if (qName.equalsIgnoreCase("location"))
            location = true;
    }
/*
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }*/

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (firstName) {
            System.out.println("First Name: " + new String(ch, start, length));
            firstName = false;
        } else if (lastName) {
            System.out.println("Last Name: " + new String(ch, start, length));
            lastName = false;
        } else if (location) {
            System.out.println("loc: " + new String(ch, start, length));
            location = false;
        }
    }
}