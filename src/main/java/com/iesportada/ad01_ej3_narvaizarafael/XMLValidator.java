package com.iesportada.ad01_ej3_narvaizarafael;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class XMLValidator {
    private static String XML_FILE = "";
    public static final String SCHEMA_FILE = "servicios_facturapp.xsd";

    public static String getXmlFile() {
        return XML_FILE;
    }

    public static void setXmlFile(String xmlFile) {
        XML_FILE = xmlFile;
    }

    public void validateXML() {
        XMLValidator XMLValidator = new XMLValidator();
        boolean valid = XMLValidator.validate(getXmlFile(), SCHEMA_FILE);

        System.out.println("");
        System.out.printf("%s validation = %b.", getXmlFile(), valid);
        System.out.println("");
    }

    private boolean validate(String xmlFile, String schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(getResource(schemaFile)));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(getResource(xmlFile))));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getResource(String filename) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }
}
