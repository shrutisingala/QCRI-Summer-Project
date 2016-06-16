/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.resources;


/**
 *
 * @author shrutisingala
 */

//import java.util.Date;


public class AlertMessage {
    
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
    String alertSignificance;
    String alertDescription;
    

  public int getAlertID() {
    return alertID;
  }

  public void setAlertID(int alertID) {
    this.alertID = alertID;
  }

  public String getAlertType() {
    return alertType;
  }

  public void setAlertType(String alertType) {
    this.alertType = alertType;
  }

  public String getAlertTime() {
    return alertTime;
  }

  public void setAlertTime(String alertTime) {
    this.alertTime = alertTime;
  }
  
  public String getAlertSeverityUnit() {
    return alertSeverityUnit;
  }

  public void setAlertSeverityUnit(String alertSeverityUnit) {
    this.alertSeverityUnit = alertSeverityUnit;
  }
  
  public float getAlertSeverityValue() {
    return alertSeverityValue;
  }

  public void setAlertSeverityValue(float alertSeverityValue) {
    this.alertSeverityValue = alertSeverityValue;
  }
  
  public String getAlertPopulationUnit() {
    return alertPopulationUnit;
  }

  public void setAlertPopulationUnit(String alertPopulationUnit) {
    this.alertPopulationUnit = alertPopulationUnit;
  }
  
  public int getAlertPopulationValue() {
    return alertPopulationValue;
  }

  public void setAlertPopulationValue(int alertPopulationValue) {
    this.alertPopulationValue = alertPopulationValue;
  }
    
  public float getAlertPointLat() {
    return alertPointLat;
  }

  public void setAlertPointLat(float alertPointLat) {
    this.alertPointLat = alertPointLat;
  }
  
  public float getAlertPointLong() {
    return alertPointLong;
  }

  public void setAlertPointLong(float alertPointLong) {
    this.alertPointLong = alertPointLong;
  }
  
  public String getAlertCalculationType() {
        return alertCalculationType;
    }
  
  public void setAlertCalculationType(String alertCalculationType) {
        this.alertCalculationType = alertCalculationType;
    }

  public String getAlertCountry() {
        return alertCountry;
    }
  
  public void setAlertCountry(String alertCountry) {
        this.alertCountry = alertCountry;
    }
  
  public String getAlertSignificance() {
    return alertSignificance;
  }
  
  public void setAlertSignificance(String alertSignificance) {
        this.alertSignificance = alertSignificance;
    }
  
  public String getAlertDescription() {
    return alertDescription;
  }
  
  public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

  @Override
  public String toString() {
    
  
    return "\n\"" + alertID + "\", \"" + alertType + "\", \"" + alertTime + "\", \"" + alertSeverityUnit + "\", \"" + alertSeverityValue + "\", \"" +alertPopulationUnit + "\", \"" +alertPopulationValue + "\", \"" + alertPointLat + "\", \"" + alertPointLong  + "\", \"" + alertCalculationType  + "\", \"" + alertSignificance + "\", \""+ alertCountry + "\"";
  
  }
}
