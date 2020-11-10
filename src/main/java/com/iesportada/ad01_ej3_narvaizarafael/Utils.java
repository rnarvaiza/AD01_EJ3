/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesportada.ad01_ej3_narvaizarafael;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;


/**
 *
 * @author Rafa Narvaiza
 */
public class Utils {

    private String filePathXML="";



    private static int CANTIDAD_REGISTROS_AÑADIDOS = 3;

    private ArrayList<Servicio> modifiedCollection = new ArrayList<>();



    public void readXML() {

        try {
            File inputFile = new File(getFilePathXML());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Elemento root: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Servicio");

            for (int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("\n Elemento actual :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    int orden = Integer.valueOf(eElement.getElementsByTagName("Orden").item(0).getTextContent());
                    String codigo = eElement.getElementsByTagName("Codigo").item(0).getTextContent();
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String profesional = eElement.getElementsByTagName("Profesional").item(0).getTextContent();
                    int precio = Integer.valueOf(eElement.getElementsByTagName("Precio").item(0).getTextContent());
                    System.out.println(orden);
                    System.out.println(codigo);
                    System.out.println(nombre);
                    System.out.println(profesional);
                    System.out.println(precio);

                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Servicio>xmlSavedOnArrayList() {
        ArrayList<Servicio> servicioArrayList = new ArrayList<>();

        try {
            File inputFile = new File(getFilePathXML());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(inputFile);
            //doc.getDocumentElement().normalize();
            System.out.println("Elemento root: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Servicio");


            for (int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("\n Elemento actual :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    int orden = Integer.valueOf(eElement.getElementsByTagName("Orden").item(0).getTextContent());
                    String codigo = eElement.getElementsByTagName("Codigo").item(0).getTextContent();
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String profesional = eElement.getElementsByTagName("Profesional").item(0).getTextContent();
                    int precio = Integer.valueOf(eElement.getElementsByTagName("Precio").item(0).getTextContent());
                    System.out.println(orden);
                    System.out.println(codigo);
                    System.out.println(nombre);
                    System.out.println(profesional);
                    System.out.println(precio);

                    Servicio servicio = new Servicio(orden, codigo, nombre, profesional, precio);

                    servicioArrayList.add(servicio);
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return servicioArrayList;
    }




    public void printNewCollection() throws ParserConfigurationException {
        try{
            File file = new File(getFilePathXML());
            DocumentBuilderFactory creador = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = creador.newDocumentBuilder();
            DOMImplementation implementacion = constructor.getDOMImplementation();
            Document documento = constructor.newDocument();
            Element elementoRaiz = documento.createElement("Servicios");
            documento.appendChild(elementoRaiz);

            for (int i = 0; i < getModifiedCollection().size(); i++){
                Element elementoServicio = documento.createElement("Servicio");
                elementoRaiz.appendChild(elementoServicio);

                Element elementoOrden = documento.createElement("Orden");
                elementoServicio.appendChild(elementoOrden);
                Text valorOrden = documento.createTextNode(String.valueOf(getModifiedCollection().get(i).getOrden()));
                elementoOrden.appendChild(valorOrden);

                Element elementoCodigo = documento.createElement("Codigo");
                elementoServicio.appendChild(elementoCodigo);
                Text valorCodigo = documento.createTextNode(getModifiedCollection().get(i).getCodigo());
                elementoCodigo.appendChild(valorCodigo);

                Element elementoNombre = documento.createElement("Nombre");
                elementoServicio.appendChild(elementoNombre);
                Text valorNombre = documento.createTextNode(getModifiedCollection().get(i).getNombre());
                elementoNombre.appendChild(valorNombre);

                Element elementoProfesional = documento.createElement("Profesional");
                elementoServicio.appendChild(elementoProfesional);
                Text valorProfesional = documento.createTextNode(getModifiedCollection().get(i).getAutor());
                elementoProfesional.appendChild(valorProfesional);

                Element elementoPrecio = documento.createElement("Precio");
                elementoServicio.appendChild(elementoPrecio);
                Text valorPrecio = documento.createTextNode(String.valueOf(getModifiedCollection().get(i).getPrecio()));
                elementoPrecio.appendChild(valorPrecio);

            }
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, resultado);

        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }

    }


    public ArrayList<Servicio> getModifiedCollection() {
        return modifiedCollection;
    }
    public void setModifiedCollection(ArrayList<Servicio> modifiedCollection){
        this.modifiedCollection = modifiedCollection;
    }

    public String getFilePathXML() {
        return filePathXML;
    }

    public void setFilePathXML(String filePathXML) {
        this.filePathXML = filePathXML;
    }


    public static class Servicio {


        private int orden;
        private int precio;
        private String codigo;
        private String nombre;
        private String autor;
        public Servicio(int orden, String codigo, String nombre, String autor, int precio){

            super();
            setPrecio(precio);
            setAutor(autor);
            setNombre(nombre);
            setCodigo(codigo);
            setOrden(orden);
        }

        public Servicio(){
            this(0,"","","",0);
        }

        public int getOrden() {
            return orden;
        }

        public void setOrden(int orden) {
            this.orden = orden;
        }

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        @Override

        public String toString(){
            return "Servicio [Orden=" + orden
                    + ", Codigo = " + codigo
                    + ", Servicio = " + nombre
                    + ", Autor = " + autor
                    + ", Precio = " + precio + "]";
        }
    }
}
