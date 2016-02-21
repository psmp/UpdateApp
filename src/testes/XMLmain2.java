/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author psmp
 */
public class XMLmain2 {
    public static void main(String[] args) {
      boolean bnome = false;
      boolean bnumero = false;
      boolean bserver = false;
      try {
         XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("config/config.xml"));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                   case XMLStreamConstants.START_ELEMENT:
                      StartElement startElement = event.asStartElement();
                      String qName = startElement.getName().getLocalPart();
                      if (qName.equalsIgnoreCase("empresa")) {
                         System.out.println("Start Element : empresa");
                         Iterator<Attribute> attributes = startElement.getAttributes();
                      } else if (qName.equalsIgnoreCase("nome")) {
                         bnome = true;
                      } else if (qName.equalsIgnoreCase("numero")) {
                         bnumero = true;
                      } else if (qName.equalsIgnoreCase("ftp")) {
                         StartElement startElement2 = event.asStartElement();
                         String qName2 = startElement2.getName().getLocalPart();
                         System.out.println("Start Element : ftp");
                         Iterator<Attribute> attributes2 = startElement2.getAttributes();
                            if (qName2.equalsIgnoreCase("server")) {
                                System.out.println("Aqui!");
                                bserver = true;
                                }
                      }
                      break;
                   case XMLStreamConstants.CHARACTERS:
                      Characters characters = event.asCharacters();
                      if(bnome){
                         System.out.println("nome: " 
                         + characters.getData());
                         bnome = false;
                      }
                      if(bnumero){
                         System.out.println("numero: " 
                         + characters.getData());
                         bnumero = false;
                      }
                      if(bserver){
                         System.out.println("server: " 
                         + characters.getData());
                         bserver = false;
                      }
                      break;
                   case  XMLStreamConstants.END_ELEMENT:
                      EndElement endElement = event.asEndElement();
                      if(endElement.getName().getLocalPart().equalsIgnoreCase("empresa")){
                         System.out.println("End Element : student");
                         System.out.println();
                      }
                      break;
                }		    
             }
          } catch (FileNotFoundException e) {
             e.printStackTrace();
          } catch (XMLStreamException e) {
             e.printStackTrace();
       }
    }
    
}
