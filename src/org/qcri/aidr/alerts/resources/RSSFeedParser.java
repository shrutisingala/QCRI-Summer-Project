/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import static java.sql.Types.NULL;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
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
    static final String ALERTPOINT = "point";
    static final String ALERTCALCULATIONTYPE = "calculationtype";
    static final String ALERTCOUNTRY = "country";

    static final String ITEM = "item";

    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
            //System.out.println("inside the rssFeedparser constructor");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Alerts readAlert() {
        Alerts alert = null;
        try {
            //System.out.println("inside readalert parser");
            boolean isAlertHeader = true;

            // Set header values intial to the empty string
            int alertID = NULL;
            String alertType = "";
            String alertTime = "";
            String alertSeverityUnit = "";
            float alertSeverityValue = 0;
            String alertPopulationUnit = "";
            int alertPopulationValue = 0;
            float alertPointLat = 0;
            float alertPointLong = 0;
            String alertCalculationType = "";
            String alertCountry = "";
            String alertSignificance = "white";
            String alertDescription = "";

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
                    StartElement element = (StartElement) event;

                    switch (localPart) {
                        case ITEM:
                            if (isAlertHeader) {
                                isAlertHeader = false;
                                //alert = new Alerts(alertID, alertType, alertTime, alertSeverityUnit, alertSeverityValue, alertPopulationUnit, alertPopulationValue, alertPointLat, alertPointLong, alertCalculationType, alertCountry);
                                alert = new Alerts();
                            }
                            event = eventReader.nextEvent();
                            break;

                        case ALERTTYPE:
                            alertType = getStringData(event, eventReader);
                            break;
                        case ALERTTIME:
                            alertTime = getStringData(event, eventReader);
                            break;
                        case ALERTSEVERITY:
                            boolean flag = true;
                            Iterator iterator = element.getAttributes();
                            while (iterator.hasNext()) {
                                Attribute attribute = (Attribute) iterator.next();
                                String value = attribute.getValue();
                                if (flag) {
                                    alertSeverityUnit = value;
                                } else {
                                    alertSeverityValue = Float.parseFloat(value);
                                }
                                flag = false;

                            }
                            break;

                        case ALERTPOPULATION:
                            //boolean 
                            flag = true;
                            Iterator iterator2 = element.getAttributes();
                            while (iterator2.hasNext()) {
                                Attribute attribute = (Attribute) iterator2.next();
                                String value = attribute.getValue();
                                if (flag) {
                                    alertPopulationUnit = value;
                                } else {
                                    alertPopulationValue = Integer.parseInt(value);
                                }
                                flag = false;

                            }

                            break;

                        case ALERTPOINT:
                            String point = getStringData(event, eventReader);
                            int i = 1;

                            String[] arr = point.split(" ");

                            for (String ss : arr) {
                                if (i == 1) {
                                    alertPointLat = Float.parseFloat(ss);
                                    i = 8;
                                } else {
                                    alertPointLong = Float.parseFloat(ss);
                                }

                            }
                            break;

                        case ALERTCALCULATIONTYPE:
                            alertCalculationType = getStringData(event, eventReader);
                            break;
                        case ALERTCOUNTRY:
                            alertCountry = getStringData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        AlertMessage message = new AlertMessage();
                        message.setAlertID(alertID);
                        message.setAlertType(alertType);
                        message.setAlertTime(alertTime);
                        message.setAlertSeverityUnit(alertSeverityUnit);
                        message.setAlertSeverityValue(alertSeverityValue);
                        message.setAlertPopulationUnit(alertPopulationUnit);
                        message.setAlertPopulationValue(alertPopulationValue);
                        message.setAlertPointLat(alertPointLat);
                        message.setAlertPointLong(alertPointLong);
                        message.setAlertCalculationType(alertCalculationType);
                        message.setAlertCountry(alertCountry);
                        message.setAlertDescription(alertDescription);
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

    private String getStringData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
