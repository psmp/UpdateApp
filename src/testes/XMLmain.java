/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import core.Empresa;
import core.XMLmanager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
/**
 *
 * @author psmp
 */
public class XMLmain {
    public static void main(String[] args){
        try {
            /*
            
            try {
            File inputFile = new File("config/input.txt");
            DocumentBuilderFactory dbFactory
            = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :"
            + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            System.out.println("Student roll no : "
            + eElement.getAttribute("rollno"));
            System.out.println("First Name : "
            + eElement
            .getElementsByTagName("firstname")
            .item(0)
            .getTextContent());
            System.out.println("Last Name : "
            + eElement
            .getElementsByTagName("lastname")
            .item(0)
            .getTextContent());
            System.out.println("Nick Name : "
            + eElement
            .getElementsByTagName("nickname")
            .item(0)
            .getTextContent());
            System.out.println("Marks : "
            + eElement
            .getElementsByTagName("marks")
            .item(0)
            .getTextContent());
            }
            }
            } catch (Exception e) {
            e.printStackTrace();
            }
            */
            XMLmanager xmlm = new XMLmanager();
            Document doc = xmlm.getConfig();
            ArrayList<Empresa> listaEmpresa = new ArrayList<Empresa>();
            listaEmpresa = xmlm.loadConfig(doc);
            
            System.out.println("Num Elementos: " + listaEmpresa.size());
            
            for (Empresa emp : listaEmpresa) {
                System.out.println("Nome das empresa: " + emp.getName());
            }
            
//            for (int i=0; i<listaEmpresa.size(); i++) {
//                Empresa e = listaEmpresa.get(i);
//                System.out.println("Empresa: " + e.getName());
//                System.out.println("Numero: " + e.getEnterprise());
//            }
            
        } catch (IOException ex) {
            Logger.getLogger(XMLmain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLmain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLmain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
}
