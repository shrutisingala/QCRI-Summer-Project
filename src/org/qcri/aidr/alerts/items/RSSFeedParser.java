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
    static final String ALERTSEVERITYUNIT = "severity.unit";
    static final String ALERTSEVERITYVALUE = "severity.value";
    static final String ALERTPOPULATIONUNIT = "population.unit";
    static final String ALERTPOPULATIONVALUE = "population.value";
    static final String ALERTPOINTLAT = "lat";
    static final String ALERTPOINTLONG = "long";
    static final String ALERTCALCULATIONTYPE = "calculationtype";
    static final String ALERTCOUNTRY = "country";

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
            int alertID = 0;
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
                                alert = new Alerts(alertID, alertType, alertTime, alertSeverityUnit, alertSeverityValue, alertPopulationUnit, alertPopulationValue, alertPointLat, alertPointLong, alertCalculationType, alertCountry);
                            }
                            event = eventReader.nextEvent();
                            break;
                        //case ALERTID:
                        //alertID = getIntegerData(event, eventReader);
                        //break;
                        case ALERTTYPE:
                            alertType = getStringData(event, eventReader);
                            break;
                        case ALERTTIME:
                            alertTime = getStringData(event, eventReader);
                            break;
                        case ALERTSEVERITYUNIT:
                            alertSeverityUnit = getStringData(event, eventReader);
                            break;
                        case ALERTSEVERITYVALUE:
                            alertSeverityValue = getFloatData(event, eventReader);
                            break;
                        case ALERTPOPULATIONUNIT:
                            alertPopulationUnit = getStringData(event, eventReader);
                            break;
                        case ALERTPOPULATIONVALUE:
                            alertPopulationValue = getIntegerData(event, eventReader);
                            break;
                        case ALERTPOINTLAT:
                            alertPointLat = getFloatData(event, eventReader);
                            break;
                        case ALERTPOINTLONG:
                            alertPointLong = getFloatData(event, eventReader);
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

    private char getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        char res;
        res = result.charAt(0);
        return res;
    }

    private int getIntegerData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        int res;
        res = Integer.parseInt(result);
        return res;
    }

    private float getFloatData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        float res;
        res = Float.parseFloat(result);
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
