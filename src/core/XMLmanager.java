/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class XMLmanager {
    
    public File xmlfile, outputfile;
    public Empresa empr;
    public ArrayList<Empresa> Lempr;
    public Document doc;
    
    public Document getConfig() throws IOException, ParserConfigurationException, SAXException {
        
        xmlfile = new File("config/config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(xmlfile);
        return doc;
    }
    
    public ArrayList<Empresa> loadConfig(Document doc){
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        // Empresa
        NodeList nListEmpr = doc.getElementsByTagName("empresa");
        Lempr = new ArrayList<Empresa>();
       
        System.out.println("----------------------------");
        for (int temp = 0; temp < nListEmpr.getLength(); temp++) {
            empr = new Empresa();
            Node nNode = nListEmpr.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                empr.setName(eElement.getElementsByTagName("nome").item(0).getTextContent());
                System.out.println("nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent());
                empr.setEnterprise(Integer.parseInt(eElement.getElementsByTagName("numero").item(0).getTextContent()));
                System.out.println("numero : " + eElement.getElementsByTagName("numero").item(0).getTextContent());
                // FTP
                NodeList nListFTP = doc.getElementsByTagName("ftp");
                for (int tempFTP = 0; tempFTP < nListFTP.getLength(); tempFTP++) {
                    Node nNodeFTP = nListFTP.item(temp);
                    if (nNodeFTP.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementFTP = (Element) nNodeFTP;
                        empr.setFtpServer(eElementFTP.getElementsByTagName("server").item(0).getTextContent());
                        System.out.println("Server : " + eElementFTP.getElementsByTagName("server").item(0).getTextContent());
                        empr.setFtpPort(Integer.parseInt(eElementFTP.getElementsByTagName("port").item(0).getTextContent()));
                        System.out.println("Port : " + eElementFTP.getElementsByTagName("port").item(0).getTextContent());
                        empr.setFtpUsername(eElementFTP.getElementsByTagName("username").item(0).getTextContent());
                        System.out.println("Username : " + eElementFTP.getElementsByTagName("username").item(0).getTextContent());
                        empr.setFtpPassword(eElementFTP.getElementsByTagName("password").item(0).getTextContent());
                        System.out.println("Password : " + eElementFTP.getElementsByTagName("password").item(0).getTextContent());
                    }
                    break;
                }
                // Compact
                NodeList nListCompact = doc.getElementsByTagName("compact");
                for (int tempC = 0; tempC < nListCompact.getLength(); tempC++) {
                    Node nNodeCompact = nListCompact.item(temp);
                    if (nNodeCompact.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementCompact = (Element) nNode;
                        empr.setCompact(eElementCompact.getElementsByTagName("dutyplan").item(0).getTextContent());
                        System.out.println("Dutyplan : " + eElementCompact.getElementsByTagName("dutyplan").item(0).getTextContent());
                        empr.setPassesBusiness(eElementCompact.getElementsByTagName("passesbusiness").item(0).getTextContent());
                        System.out.println("PassesBusiness : " + eElementCompact.getElementsByTagName("passesbusiness").item(0).getTextContent());
                        empr.setXmlBynary(eElementCompact.getElementsByTagName("xmlbinary").item(0).getTextContent());
                        System.out.println("XMLtoBinary : " + eElementCompact.getElementsByTagName("xmlbinary").item(0).getTextContent());
                    }
                    break;
                }
                // Place
                NodeList nListPlace = doc.getElementsByTagName("place");
                for (int tempP = 0; tempP < nListPlace.getLength(); tempP++) {
                    Node nNodePlace = nListPlace.item(temp);
                    if (nNodePlace.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementPlace = (Element) nNode;
                        empr.setPlace(eElementPlace.getElementsByTagName("leitora").item(0).getTextContent());
                        System.out.println("Leitora : " + eElementPlace.getElementsByTagName("leitora").item(0).getTextContent());
                    }
                    break;
                }
                // Reflex
                NodeList nListReflex = doc.getElementsByTagName("reflex");
                for (int tempR = 0; tempR < nListReflex.getLength(); tempR++) {
                    Node nNodeReflex = nListReflex.item(temp);
                    if (nNodeReflex.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementReflex = (Element) nNode;
                        empr.setDcar(eElementReflex.getElementsByTagName("dcar").item(0).getTextContent());
                        System.out.println("Dcar : " + eElementReflex.getElementsByTagName("dcar").item(0).getTextContent());
                        empr.setReflexINI(eElementReflex.getElementsByTagName("reflexINI").item(0).getTextContent());
                        System.out.println("reflexINI : " + eElementReflex.getElementsByTagName("reflexINI").item(0).getTextContent());
                        empr.setTube(eElementReflex.getElementsByTagName("tube").item(0).getTextContent());
                        System.out.println("tube : " + eElementReflex.getElementsByTagName("tube").item(0).getTextContent());
                    }
                }
                // mobileReflex
                NodeList nListmobileReflex = doc.getElementsByTagName("mobileReflex");
                for (int tempmR = 0; tempmR < nListmobileReflex.getLength(); tempmR++) {
                    Node nNodemR = nListmobileReflex.item(temp);
                    if (nNodemR.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementmR = (Element) nNode;
                        empr.setMobileReflex(eElementmR.getElementsByTagName("mR").item(0).getTextContent());
                        System.out.println("mR : " + eElementmR.getElementsByTagName("mR").item(0).getTextContent());
                        empr.setMobileReflex_ws(eElementmR.getElementsByTagName("webservice").item(0).getTextContent());
                        System.out.println("webservice : " + eElementmR.getElementsByTagName("webservice").item(0).getTextContent());
                    }
                    break;
                }
                Lempr.add(empr);
                System.out.println("----------------------------");
            }
            
        }
        return Lempr;
    }
}
