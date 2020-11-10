package com.iesportada.ad01_ej3_narvaizarafael;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main (String[]args){

        //  final String filePathXSD = "C:\\Users\\s0ck3\\Documents\\NetBeansProjects\\AD01_EJ3_NarvaizaRafael\\src\\main\\resources\\servicios_facturapp.xsd";
        final String filePathXMLOutput = "C:\\Users\\s0ck3\\Documents\\NetBeansProjects\\AD01_EJ3_NarvaizaRafael\\src\\main\\resources\\servicios_facturapp_v2.xml";
        final String filePathXML = "C:\\Users\\s0ck3\\Documents\\NetBeansProjects\\AD01_EJ3_NarvaizaRafael\\src\\main\\resources\\servicios_facturapp.xml";



        Scanner scannerForInts = new Scanner(System.in);
        Scanner scannerForStrings = new Scanner(System.in);
        Scanner scannerForMenu = new Scanner(System.in);
        Utils utils = new Utils();
        XMLValidator xml = new XMLValidator();

        int orden;
        int precio;
        int temp = 0;
        int opcion =0;
        String codigo;
        String nombre;
        String autor;
        String xmlFile ="servicios_facturapp.xml";
        String xmlFileNew = "servicios_facturapp_v2.xml";
        boolean continuation = true;

        ArrayList<Utils.Servicio> modifiedCollection = new ArrayList<>();



        System.out.println("En este programa podrás trabajar con ficheros XML. Puedes leerlos, añadir nuevos servicios o validarlos");
        do {
            System.out.println("");
            System.out.println("Menú de opciones ");
            System.out.println("#1 Para añadir nodos al fichero XML.");
            System.out.println("#2 Para leer el fichero XML.");
            System.out.println("#3 Para validar el fichero XML.");
            System.out.println("#4 Salir.");
            try{
                do{
                    System.out.println("");
                    System.out.println("############################");
                    System.out.println("Por favor, elija una opción: ");
                    opcion = scannerForMenu.nextInt();
                }while(opcion < 1 || opcion > 5);

                switch (opcion){
                    case 1:
                        utils.setFilePathXML(filePathXML);
                        modifiedCollection = utils.xmlSavedOnArrayList();
                        while(temp < 3 ){
                            Utils.Servicio servicio = new Utils.Servicio();
                            System.out.println("\n----------------------------");
                            System.out.println("\n Menú de adición de nodos. Aquí podrá añadir 3 nodos. Aparecerán en el fichero servicios_facturapp_v2");
                            System.out.println("\n A continuación, añada un nuevo servicio:");
                            System.out.println("Número de orden");
                            //orden = scannerForInts.nextInt();
                            orden = 1;
                            servicio.setOrden(orden);

                            System.out.println("Código: ");
                            //codigo = scannerForStrings.next();
                            codigo ="1";
                            servicio.setCodigo(codigo);

                            System.out.println("Nombre: ");
                            nombre = "aa";
                            //nombre = scannerForStrings.next();
                            servicio.setNombre(nombre);

                            System.out.println("Autor: ");
                            autor = "ss";
                            //autor = scannerForStrings.next();
                            servicio.setAutor(autor);

                            System.out.println("Precio: ");
                            //precio = scannerForInts.nextInt();
                            precio = 2;
                            servicio.setPrecio(precio);


                            modifiedCollection.add(servicio);
                            System.out.println(servicio.toString() + " Ha sido añadido a la colección");

                            temp ++;
                        }
                        utils.setModifiedCollection(modifiedCollection);
                        try {
                            utils.setFilePathXML(filePathXMLOutput);
                            utils.printNewCollection();
                        } catch (ParserConfigurationException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("\n----------------------------");
                        System.out.println("\n Menú de lectura de ficheros");
                        System.out.println("\n A continuación, se leerá el fichero servicios_facturapp_v2");
                        utils.setFilePathXML(filePathXMLOutput);
                        utils.readXML();

                        break;

                    case 3:
                        System.out.println("\n----------------------------");
                        System.out.println("A continuación vamos a proceder a la validación del archivo 'servicios_facturapp.xml' contra su xsd:");
                        XMLValidator.setXmlFile(xmlFile);
                        xml.validateXML();

                        break;

                    case 4:
                        continuation=false;
                        System.exit(0);
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }

        }while (continuation);



    }
}
