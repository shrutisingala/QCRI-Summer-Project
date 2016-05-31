/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shrutisingala
 */
public class Alerts {

    int alertID;
    String alertType;
    String alertTime;
    String alertSeverityUnit;
    float alertSeverityValue;
    String alertPopulationUnit;
    int alertPopulationValue;
    float alertPointLat;
    float alertPointLong;
    String alertCalculationType;
    String alertCountry;

    final List<AlertMessage> entries = new ArrayList<AlertMessage>();

    public Alerts(int alertID, String alertType, String alertTime, String alertSeverityUnit, float alertSeverityValue, String alertPopulationUnit, int alertPopulationValue, float alertPointLat, float alertPointLong, String alertCalculationType, String alertCountry) {
        this.alertID = alertID;
        this.alertType = alertType;
        this.alertTime = alertTime;
        this.alertSeverityUnit = alertSeverityUnit;
        this.alertSeverityValue = alertSeverityValue;
        this.alertPopulationUnit = alertPopulationUnit;
        this.alertPopulationValue = alertPopulationValue;
        this.alertPointLat = alertPointLat;
        this.alertPointLong = alertPointLong;
        this.alertCalculationType = alertCalculationType;
        this.alertCountry = alertCountry;
    }

    public List<AlertMessage> getMessages() {
        //System.out.println(entries);
        return entries;
    }

    public int getAlertID() {
        return alertID;
    }

    public String getAlertType() {
        return alertType;
    }

    public String getAlertTime() {
        return alertTime;
    }

    public String getAlertSeverityUnit() {
        return alertSeverityUnit;
    }

    public float getAlertSeverityValue() {
        return alertSeverityValue;
    }

    public String getAlertPopulationUnit() {
        return alertPopulationUnit;
    }

    public int getAlertPopulationValue() {
        return alertPopulationValue;
    }

    public float getAlertPointLat() {
        return alertPointLat;
    }

    public float getAlertPointLong() {
        return alertPointLong;
    }

    public String getAlertCalculationType() {
        return alertCalculationType;
    }
    
    public String getAlertCountry() {
        return alertCountry;
    }

    @Override
    public String toString() {
    
  
    return "\n\"" + alertID + "\", \"" + alertType + "\", \"" + alertTime + "\", \"" + alertSeverityUnit + "\", \"" + alertSeverityValue + "\", \"" +alertPopulationUnit + "\", \"" +alertPopulationValue + "\", \"" + alertPointLat + "\", \"" + alertPointLong  + "\", \"" + alertCalculationType  + "\", \"" + alertCountry + "\"";
  
  }

}
