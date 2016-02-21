/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 *
 * @author psmp
 */
public class XMLmain3 {
    public static void main(String argv[]) {
        try {

           File fXmlFile = new File("config/config.xml");
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(fXmlFile);

           //optional, but recommended
           //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
           doc.getDocumentElement().normalize();

           System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

           NodeList nList = doc.getElementsByTagName("empresa");

           System.out.println("----------------------------");
           // Empresa
           for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                     Element eElement = (Element) nNode;
                     //System.out.println("Staff id : " + eElement.getAttribute("id"));
                     System.out.println("nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent());
                     System.out.println("numero : " + eElement.getElementsByTagName("numero").item(0).getTextContent());
                     // FTP
                     NodeList nList2 = doc.getElementsByTagName("ftp");
                     for (int temp2 = 0; temp2 < nList2.getLength(); temp2++) {

                             Node nNode2 = nList2.item(temp);

                             System.out.println("\nCurrent Element :" + nNode2.getNodeName());
                             if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                                 Element eElement2 = (Element) nNode;
                                 System.out.println("Server : " + eElement.getElementsByTagName("server").item(0).getTextContent());
                                 System.out.println("Port : " + eElement.getElementsByTagName("port").item(0).getTextContent());
                                 System.out.println("Username : " + eElement.getElementsByTagName("username").item(0).getTextContent());
                                 System.out.println("Password : " + eElement.getElementsByTagName("password").item(0).getTextContent());
                             }
                     }
                        
                        // Compact
                        NodeList nList3 = doc.getElementsByTagName("compact");
                        for (int temp3 = 0; temp3 < nList3.getLength(); temp3++) {

                                Node nNode3 = nList3.item(temp);
                                
                                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement3 = (Element) nNode;
                                    System.out.println("Dutyplan : " + eElement.getElementsByTagName("dutyplan").item(0).getTextContent());
                                    System.out.println("passesbusiness : " + eElement.getElementsByTagName("passesbusiness").item(0).getTextContent());
                                }
                        }
                        // Place
                        NodeList nListPlace = doc.getElementsByTagName("place");
                        for (int tempPlace = 0; tempPlace < nListPlace.getLength(); tempPlace++) {

                                Node nPlace = nListPlace.item(temp);
                                
                                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement3 = (Element) nNode;
                                    System.out.println("leitora : " + eElement.getElementsByTagName("leitora").item(0).getTextContent());
                                }
                        }



                   }
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
