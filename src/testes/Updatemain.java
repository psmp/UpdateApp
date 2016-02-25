/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import core.Empresa;
import core.XMLmanager;
import interfacesUI.Update;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author ami
 */
public class Updatemain {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        XMLmanager xmlconfig = new XMLmanager();
        Document file = xmlconfig.getConfig();
        ArrayList<Empresa> lempr = xmlconfig.loadConfig(file);
        Update procUpdate = new Update();
        
        for (Empresa empresa : lempr) {
                if (empresa.getName().equals("STAC")) {
                    System.out.println("Empresa: " + empresa.getName());
                    System.out.println("Compact: " + empresa.getCompact());
                    if (procUpdate.validateUpdate(empresa, "compact")) {
                        System.out.println("Existe pasta");
                    }
                    else {System.out.println("Não existe pasta");}
                }
            }
        
        
    }
}
