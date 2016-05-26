/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import static java.sql.Types.NULL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;



/**
 *
 * @author shrutisingala
 */
public class RSSFeedParser {
  
  
 
  static final String ALERTID = "eventid";
  static final String ALERTTYPE = "eventtype";
  static final String ALERTTIME = "pubDate";
  static final String ALERTSEVERITY = "severity";
  static final String ALERTPOPULATION = "population";
  static final String ITEM = "item";


  final URL url;
  
  public RSSFeedParser(String feedUrl) {
    try {
      this.url = new URL(feedUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public Alerts readAlert() {
    Alerts alert = null;
    try {
      boolean isAlertHeader = true;
      
      // Set header values intial to the empty string
      
      int alertID = NULL;
      String alertType = "";
      String alertTime = "";
      String alertSeverity = "";
      String alertPopulation = "";

      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = read();
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // read the XML document
      
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          String localPart = event.asStartElement().getName()
              .getLocalPart();
          switch (localPart) {
          case ITEM:
            if (isAlertHeader) {
              isAlertHeader = false;
              alert = new Alerts(alertID, alertType, alertTime, alertSeverity, alertPopulation);
            }
            event = eventReader.nextEvent();
            break;
          //case ALERTID:
            //alertID = getIntegerData(event, eventReader);
            //break;
          case ALERTTYPE:
            alertType = getCharacterData(event, eventReader);
            break;
          case ALERTTIME:
            alertTime = getCharacterData(event, eventReader);
            break;
          case ALERTSEVERITY:
            alertSeverity = getCharacterData(event, eventReader);
            break;
          case ALERTPOPULATION:
            alertPopulation = getCharacterData(event, eventReader);
            break;
          }
        } else if (event.isEndElement()) {
          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
            AlertMessage message = new AlertMessage();
            message.setAlertID(alertID);
            message.setAlertType(alertType);
            message.setAlertTime(alertTime);
            message.setAlertSeverity(alertSeverity);
            message.setAlertPopulation(alertPopulation);
            alert.getMessages().add(message);
            event = eventReader.nextEvent();
            continue;
          }
        }
      }
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return alert;
  }

  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
      throws XMLStreamException {
    String result = "";
    event = eventReader.nextEvent();
    if (event instanceof Characters) {
      result = event.asCharacters().getData();
    }
    return result;
  }
  
  private int getIntegerData(XMLEvent event, XMLEventReader eventReader)
      throws XMLStreamException {
    String result = "";
    event = eventReader.nextEvent();
    if (event instanceof Characters) {
      result = event.asCharacters().getData();
    }
    int res;
    res=Integer.parseInt(result);
    return res;
  }

  private InputStream read() {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
